package com.soap.soapTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Name;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.util.logging.Logger;

public class App 
{
    public static void main( String[] args ) // 1
    {
    	// SOAP Standard 는 SOAP Envelope, SOAP Head, SOAP Body
    	//https://blog.naver.com/spdlqjdudghl/140202989836  >>>>> 이거 참고해보기
    	
    	
    	// https://m.blog.naver.com/rlagyska3319/221307877313
    	String soapEndpointUrl = "http://localhost:8888/soap";
//    	String soapEndpointUrl = "http://localhost:8080/wsdl/OrderProcess";
        String soapAction = "";

        callSoapWebService(soapEndpointUrl, soapAction);
    }

    
    private static void callSoapWebService(String soapEndpointUrl, String soapAction) {  // 2
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);

            // Print the SOAP Response
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();
            System.out.println();
            System.out.println("Response SOAP Data:");
            //해당 부분에서 필요한 데이터를 추출한다.
            Iterator it = soapResponse.getSOAPBody().getChildElements();
            while (it.hasNext()) {
                SOAPBodyElement bodyElement = (SOAPBodyElement) it.next();
                Iterator it2 = bodyElement.getChildElements();
                while (it2.hasNext()) {
                    SOAPElement element2 = (SOAPElement) it2.next();
//                    for (int step=0; step<element2.getChildNodes().getLength(); step++) {
//                    	System.out.println(element2.getChildNodes().item(step).getNodeName() + " : " + element2.getChildNodes().item(step).getTextContent());
//                    }
                    Iterator it3 = element2.getChildElements();
                    while (it3.hasNext()) {
                    	SOAPElement element3 = (SOAPElement) it3.next();
                    	System.out.println(element3.getLocalName() + " : " + element3.getValue());
                    }
                }
            }
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\n에러 : : : Error occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSOAPRequest(String soapAction) throws Exception {  // 3
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
//        createSoapEnvelope(soapMessage);
        createSoapEnvelopeService(soapMessage);
                
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");

        return soapMessage;
    }

    
    
    private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {  // 4
        SOAPPart soapPart = soapMessage.getSOAPPart();
        String myNamespace = "wsdl";
        String myNamespaceURI = "http://soapListener.soap.com/";
        
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

//        SOAP Body 서버에서 원하는 태그로 만들어서 보낸다
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("getEmployeeById", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("arg0");
        soapBodyElem1.addTextNode("1");
    }
//    
    
    	
    public static void createSoapEnvelopeService(SOAPMessage soapMessage) throws SOAPException, SAXException, IOException {
    	SOAPPart soapPart = soapMessage.getSOAPPart();
    	SOAPEnvelope envelope = soapPart.getEnvelope();
    	String sendMessage =
			"<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wsdl=\"http://soapListener.soap.com/\">" +
			"<SOAP-ENV:Header/>" +
			"<SOAP-ENV:Body>" +
			"<wsdl:getEmployeeById>" +
			"<arg0>1</arg0>" +
			"</wsdl:getEmployeeById>" +
			"</SOAP-ENV:Body>" +
			"</SOAP-ENV:Envelope>";
    	
    	byte[] buffer = sendMessage.getBytes();
    	ByteArrayInputStream stream = new ByteArrayInputStream(buffer);
    	StreamSource source = new StreamSource(stream);
    	soapPart.setContent(source);
    }
    	
    	
    	
    	
    	
    	
    	
    	
//    	try {
//    		// https://docs.oracle.com/cd/E19340-01/820-6767/aeqgc/index.html
//    		// Get an instance of a SOAPConnectionFactory
//    		SOAPConnectionFactory myFct = SOAPConnectionFactory.newInstance();
//    		
//    		// Get a SAOP connection from the SOAPConnectionFactory object
//    		SOAPConnection myCon = myFct.createConnection();
//    		
//    		// Get a MessageFactory object to create a message
//    		MessageFactory myMsgFct = MessageFactory.newInstance();
//    		
//    		// Use the message factory to create a message
//    		SOAPMessage message = myMsgFct.createMessage();
//    		
//    		SOAPPart mySPart = message.getSOAPPart();
//    		SOAPEnvelope myEnvp = mySPart.getEnvelope();
//    		
//    		// Now, you can get the body element from the myEnvp object
//    		SOAPBody body = myEnvp.getBody();
//    		
//    		Name bodyName = envelope.createName("getEmployeeById", "1", "http://localhost:8888/soap");
//    		SOAPBodyElement gltp = body.addBodyElement(bodyName);
//    		
//    		// Now create another body element to add to the gltp element
//    		Name myContent = envelope.createName("symbol");
//    		SOAPElement mySymbol = gltp.addChildElement(myContent);
//    		
//    		// And now you can define data for the body element mySymbol
//    		mySymbol.addTextNode("SUNW");
//    		
//    		// The resulting SOAP message object is equivalent to this XML scheme
//    		/*
//    		 * <SOAP-ENV: Envelope
//				    xmlns:SOAPENV="http://schemas.xmlsoap.org/soap/envelope/">
//				        <SOAP-ENV:Body>
//				            <m:GetLastTradePrice xmlns:m="http://eztrade.com">
//				                <symbol>SUNW</symbol>
//				            </m:GetLastTradePrice>
//				 	   </SOAP-ENV:Body>
//				</SOAP-ENV: Envelope>
//				    		 * */
//    		
//    		message.saveChanges();
//    		URLEndpoint endPt = new URLEndpoint("http://localhost:8888/soap/quit");
//    		SOAPMessage reply = myCon.call(message, endPt);
//    		myCon.close();
//    		
//    	} catch(Exception e) {
//    		e.printStackTrace();
//    	}
//    	
//    	
    	

    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
//        // call server(listener)
//    	// xml 형식으로 요청 된 것을 다시 보냄.
//    	
//    	try {
//			String uri = "http://localhost:8888/soap?wsdl";
//			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();        
//	        factory.setNamespaceAware(true);             
//	        DocumentBuilder parser = factory.newDocumentBuilder();
//	 
//	        //request SOAP message DOMSource create
//	        String sendMessage = 
//	            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//	            "<root> " +
//	            "     <body>" +
//	            "         <record>" +
//	            "               ... " +
//	            "           ... " +
//	            "           ... " +
//	            "        </record>" + 
//	            "    </body>" +
//	            "</root> "; 
//	            
//	        StringReader reader = new StringReader(sendMessage);
//	        InputSource is = new InputSource(reader);
//	        Document document = parser.parse(is);
//	        DOMSource requestSource = new DOMSource(document);
//	 
//	        //SOAPMessage create
//	        MessageFactory messageFactory = MessageFactory.newInstance(); // 3. Get a MessageFactory object to create a message
//	        SOAPMessage requestSoapMessage = messageFactory.createMessage(); // 4. Use the message factory to create a message
//	        SOAPPart requestSoapPart = requestSoapMessage.getSOAPPart(); 
//	        requestSoapPart.setContent(requestSource);
//	        SOAPEnvelope myEnvp = requestSoapPart.getEnvelope(); // ===================================
//	        SOAPBody body = myEnvp.getBody();// ===================================
//	 
//	        //SOAPConnection create instance
//	        SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance(); // 1. Get an instance of a SOAPConnectionFactory
//	        SOAPConnection connection = scf.createConnection(); // 2. Get a SOAP connection from the SOAPConnectionFactory object
//	 
//	        //SOAP SEND MESSAGE
//	        SOAPMessage responseSoapMessage = connection.call(requestSoapMessage, "http://localhost:8888/soap?wsdl");
//	        ByteArrayOutputStream out = new ByteArrayOutputStream();
//	        responseSoapMessage.writeTo(out);
//	        //String soapResult = new String(out.toByteArray(), "UTF-8");
//	 
//	        SOAPBody soapBody = responseSoapMessage.getSOAPBody();  
//	 
//	        Iterator i = soapBody.getChildElements();  
//	        Node node = (Node) i.next();
//	          
//	        JSONParser jsonParser = new JSONParser();
//	        JSONObject soapResult = (JSONObject) jsonParser.parse(node.getChildNodes().item(0).getChildNodes().item(0).getNodeValue());
//	            
//	        System.out.println("e r r o r >>> " + soapResult.toString());
////	        log.debug(soapResult.toString());
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//    	
    	
//    }
    
}
