package com.maxart;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.client.transport.TransportException;
import org.uddi.api_v3.*;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Juddi {

    private UDDIClerk clerk;
    private String businessKey;
    private UDDIInquiryPortType inquiry;

    Juddi(UDDIClient client, UDDIClerk clerk, String businessKey) throws ConfigurationException, TransportException {
        this.clerk = clerk;
        Transport transport = client.getTransport("default");
        inquiry = transport.getUDDIInquiryService();
        this.businessKey = businessKey;
    }

    public String publish(String serviceName, String description, String wsdl) {
        BusinessService myService = new BusinessService();
        myService.setBusinessKey(businessKey);
        Name myServName = new Name();
        myServName.setValue(serviceName);
        myService.getName().add(myServName);

        Description myDescription = new Description();
        myDescription.setValue(description);
        myService.getDescription().add(myDescription);

        BindingTemplate myBindingTemplate = new BindingTemplate();
        AccessPoint accessPoint = new AccessPoint();
        accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
        accessPoint.setValue(wsdl);
        myBindingTemplate.setAccessPoint(accessPoint);
        BindingTemplates myBindingTemplates = new BindingTemplates();

        myBindingTemplate = UDDIClient.addSOAPtModels(myBindingTemplate);
        myBindingTemplates.getBindingTemplate().add(myBindingTemplate);
        myService.setBindingTemplates(myBindingTemplates);

        BusinessService svc = clerk.register(myService);
        if (svc == null) {
            System.out.println("Save failed!");
            System.exit(1);
        }

        return svc.getServiceKey();
    }

    public String findServiceByKey(String serviceKey) throws RemoteException, ConfigurationException, TransportException {
        BusinessService businessService = clerk.getServiceDetail(serviceKey);
        if (businessService == null) {
            return "";
        }

        return displayServiceDetails(businessService);
    }

    public List<ServiceInformation> findServiceByName(String serviceName) throws RemoteException, TransportException {
        FindService findService = new FindService();
        findService.setAuthInfo(clerk.getAuthToken());

        FindQualifiers findQualifiers = new FindQualifiers();
        findQualifiers.getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);
        findService.setFindQualifiers(findQualifiers);
        Name searchName = new Name();
        searchName.setValue("%" + serviceName + "%");
        findService.getName().add(searchName);
        ServiceList serviceList = inquiry.findService(findService);

        GetServiceDetail gsd = new GetServiceDetail();
        for (int i = 0; i < serviceList.getServiceInfos().getServiceInfo().size(); i++) {
            gsd.getServiceKey().add(serviceList.getServiceInfos().getServiceInfo().get(i).getServiceKey());
        }

        List<ServiceInformation> services = new ArrayList<>();
        ServiceDetail serviceDetail = inquiry.getServiceDetail(gsd);
        if (serviceDetail != null) {
            for (int i = 0; i < serviceDetail.getBusinessService().size(); i++) {
                ServiceInformation si = new ServiceInformation();
                si.setId(serviceDetail.getBusinessService().get(i).getServiceKey());
                si.setName(serviceDetail.getBusinessService().get(i).getName().get(0).getValue());
                si.setDescription(serviceDetail.getBusinessService().get(i).getDescription().get(0).getValue());
                si.setAccessPoint(displayServiceDetails(serviceDetail.getBusinessService().get(i)));
                services.add(si);
                System.out.println();
            }
        }

        return services;
    }

    public void deleteService(String serviceKey) {
        clerk.unRegisterService(serviceKey);
    }

    public void callService(URL url, String filename) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        URLConnection conn = url.openConnection();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(conn.getInputStream());

        TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer xform = tfactory.newTransformer();

        File myOutput = new File(filename);
        xform.transform(new DOMSource(doc), new StreamResult(myOutput));
    }

    private String displayServiceDetails(BusinessService businessService) {
        System.out.println("Name: " + ListToString(businessService.getName()));
        System.out.println("Desc: " + ListToDescString(businessService.getDescription()));
        System.out.println("Key: " + (businessService.getServiceKey()));
        System.out.println("Cat bag: " + CatBagToString(businessService.getCategoryBag()));
        if (!businessService.getSignature().isEmpty()) {
            System.out.println("Item is digitally signed");
        } else {
            System.out.println("Item is not digitally signed");
        }

        return PrintBindingTemplates(businessService.getBindingTemplates());
    }

    private String ListToString(List<Name> name) {
        StringBuilder sb = new StringBuilder();
        for (Name aName : name) {
            sb.append(aName.getValue()).append(" ");
        }

        return sb.toString();
    }

    private String ListToDescString(List<Description> name) {
        StringBuilder sb = new StringBuilder();
        for (Description aName : name) {
            sb.append(aName.getValue()).append(" ");
        }

        return sb.toString();
    }

    private String CatBagToString(CategoryBag categoryBag) {
        StringBuilder sb = new StringBuilder();
        if (categoryBag == null) {
            return "no data";
        }

        for (int i = 0; i < categoryBag.getKeyedReference().size(); i++) {
            sb.append(KeyedReferenceToString(categoryBag.getKeyedReference().get(i)));
        }

        for (int i = 0; i < categoryBag.getKeyedReferenceGroup().size(); i++) {
            sb.append("Key Ref Grp: TModelKey=");
            for (int k = 0; k < categoryBag.getKeyedReferenceGroup().get(i).getKeyedReference().size(); k++) {
                sb.append(KeyedReferenceToString(categoryBag.getKeyedReferenceGroup().get(i).getKeyedReference().get(k)));
            }
        }

        return sb.toString();
    }

    private String KeyedReferenceToString(KeyedReference item) {
        return "Key Ref: Name=" +
                item.getKeyName() +
                " Value=" +
                item.getKeyValue() +
                " tModel=" +
                item.getTModelKey() +
                System.getProperty("line.separator");
    }

    private String PrintBindingTemplates(BindingTemplates bindingTemplates) {
        String accessPoint = "";
        if (bindingTemplates == null) {
            return accessPoint;
        }

        for (int i = 0; i < bindingTemplates.getBindingTemplate().size(); i++) {
            System.out.println("Binding Key: " + bindingTemplates.getBindingTemplate().get(i).getBindingKey());
            if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint() != null) {
                accessPoint = bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getValue();
                System.out.println("Access Point: " + accessPoint + " type " + bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType());
                if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType() != null) {
                    if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType().equalsIgnoreCase(AccessPointType.END_POINT.toString())) {
                        System.out.println("Use this access point value as an invocation endpoint.");
                    }
                    if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType().equalsIgnoreCase(AccessPointType.BINDING_TEMPLATE.toString())) {
                        System.out.println("Use this access point value as a reference to another binding template.");
                    }
                    if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType().equalsIgnoreCase(AccessPointType.WSDL_DEPLOYMENT.toString())) {
                        System.out.println("Use this access point value as a URL to a WSDL document, which presumably will have a real access point defined.");
                    }
                    if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType().equalsIgnoreCase(AccessPointType.HOSTING_REDIRECTOR.toString())) {
                        System.out.println("Use this access point value as an Inquiry URL of another UDDI registry, look up the same binding template there (usage varies).");
                    }
                }
            }
        }

        return accessPoint;
    }
}
