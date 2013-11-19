package com.example.iplsa.Classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

// ir buscar jar a: http://goo.gl/Sjf1H

public class WebServiceClient {

	private String namespace="http://ead.ipleiria.pt/ucs201213/admin/report/uedws/";

	private String soapAction="urn:UEDWsService#UEDWsServiceServer#isWebserviceEnabled";
	private String operation = "isWebserviceEnabled";
	private String url ="http://ead.ipleiria.pt/admin/report/uedws/?wsdl";
	public static final String METHOD_IS_WEBSERVICE_ENABLED = "urn:UEDWsService#UEDWsServiceServer#isWebserviceEnabled";

//	public static final String METHOD_MY_LAST_SOLVED_TICKETS ="getMyLastSolvedTickets";
//	public static final String METHOD_GET_TICKET_COMMENTS_NEEDING_ATTENTION ="getTicketCommentsNeedingAttention";
//	public static final String METHOD_GET_UNASSIGNED_TICKETS ="getUnassignedTickets";
//	public static final String METHOD_GET_TICKET_BY_ID ="getTicketForSupporter";
//	public static final String METHOD_GET_COMMENTS_FOR_TICKET ="getTicketComments";
//	public static final String METHOD_MY_UNSOLVED_TICKETS ="getMyUnsolvedTickets";
//	public static final String METHOD_CREATE_TICKET_BY_SUPPORTER ="createTicketBySupporter";
//	public static final String METHOD_PING="ping";
//	public static final String METHOD_CREATE_TICKET_COMMENT = "createTicketCommentBySupporter";
//	public static final String METHOD_EDIT_TICKET_BY_SUPPORTER = "modifyTicketBySupporter";
	private String user;
	private String password;
	private String sessionKey;

	public WebServiceClient(String methodName, String user, String password ) 
	{
		this.namespace = namespace;
		this.soapAction = soapAction;
		this.url = url;	
		this.user=user;
		this.password=password;
	}

	public String connect() throws Exception {

		Boolean isEnabled= true;
		SoapObject request = new SoapObject(namespace, operation);
		PropertyInfo autenticationPropertyIsWebserviceEnabledRequest = new PropertyInfo();
		autenticationPropertyIsWebserviceEnabledRequest.setType(Boolean.class);
    	autenticationPropertyIsWebserviceEnabledRequest.setValue(isEnabled);
    	autenticationPropertyIsWebserviceEnabledRequest.setNamespace(namespace);
		request.addProperty(autenticationPropertyIsWebserviceEnabledRequest);
		PropertyInfo autenticationPropertyIsWebserviceEnabledResponse = new PropertyInfo();
		autenticationPropertyIsWebserviceEnabledResponse.setType(Boolean.class);
		autenticationPropertyIsWebserviceEnabledResponse.setName("return");
		autenticationPropertyIsWebserviceEnabledResponse.setValue(isEnabled);
		autenticationPropertyIsWebserviceEnabledResponse.setNamespace(namespace);
		request.addProperty(autenticationPropertyIsWebserviceEnabledResponse);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);

		
		HttpTransportSE httpRequest = new HttpTransportSE(url);
		httpRequest.call(soapAction, envelope);
		
		SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
		String cenas = "";
		cenas =  response.toString();
		System.out.println("\n\n------------------------>" + cenas + "<-------------------------\n\n");
		return this.sessionKey;
	}
//
//	public List<Ticket> getUnassignedTickets() throws Exception {
//
//
//		Vector soapedTicket = null;
//
//		SoapObject request = new SoapObject(namespace, METHOD_GET_UNASSIGNED_TICKETS);
//		PropertyInfo autenticationPropertySessionKey = new PropertyInfo();
//
//		autenticationPropertySessionKey.setType(String.class);
//		autenticationPropertySessionKey.setName("sessionKey");
//		autenticationPropertySessionKey.setValue(this.sessionKey);
//		request.addProperty(autenticationPropertySessionKey);
//
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
//		envelope.dotNet = false;
//		envelope.setOutputSoapObject(request);
//
//		HttpTransportSE httpRequest = new HttpTransportSE(url);
//
//		httpRequest.call(namespace+METHOD_GET_UNASSIGNED_TICKETS, envelope);
//		soapedTicket = (Vector)envelope.getResponse();
//
//		return getTicketsFromSoapList(soapedTicket);		    
//	}
//
//	public List<Ticket> getMyUnsolvedTickets() throws Exception
//	{ 
//
//		Vector soapedTicket = null;
//
//		SoapObject request = new SoapObject(namespace, METHOD_MY_UNSOLVED_TICKETS);
//		PropertyInfo propertySessionKey = new PropertyInfo();
//
//		propertySessionKey.setType(String.class);
//		propertySessionKey.setName("sessionKey");
//		propertySessionKey.setValue(this.sessionKey);
//		request.addProperty(propertySessionKey);
//
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
//		envelope.dotNet = false;
//		envelope.setOutputSoapObject(request);
//
//		HttpTransportSE httpRequest = new HttpTransportSE(url);
//
//		httpRequest.call(namespace+METHOD_MY_UNSOLVED_TICKETS, envelope);
//		soapedTicket = (Vector)envelope.getResponse();
//
//		return getTicketsFromSoapList(soapedTicket);		
//
//	}
//
//	public List<Ticket> getMyLastSolvedTickets() throws Exception
//	{ 
//
//		Vector soapedTicket = null;
//
//		SoapObject request = new SoapObject(namespace, METHOD_MY_LAST_SOLVED_TICKETS);
//		PropertyInfo propertySessionKey = new PropertyInfo();
//		PropertyInfo propertyNumberOfTickets = new PropertyInfo();
//
//		propertySessionKey.setType(String.class);
//		propertySessionKey.setName("sessionKey");
//		propertySessionKey.setValue(this.sessionKey);
//		request.addProperty(propertySessionKey);
//
//		propertyNumberOfTickets.setType(Integer.class);
//		propertyNumberOfTickets.setName("numberOfTickets");
//		propertyNumberOfTickets.setValue(10);
//		request.addProperty(propertyNumberOfTickets);
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
//		envelope.dotNet = false;
//		envelope.setOutputSoapObject(request);
//
//		HttpTransportSE httpRequest = new HttpTransportSE(url);
//
//		httpRequest.call(namespace+METHOD_MY_LAST_SOLVED_TICKETS, envelope);
//		soapedTicket = (Vector)envelope.getResponse();
//
//		return getTicketsFromSoapList(soapedTicket);		
//
//	}
//
//	public String isAvailable() throws Exception{
//
//		SoapObject request= new SoapObject(namespace, METHOD_PING);
//		SoapPrimitive soapedString;
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
//		envelope.dotNet = false;
//		envelope.setOutputSoapObject(request);
//
//		HttpTransportSE httpRequest = new HttpTransportSE(url);
//
//		httpRequest.call(namespace+METHOD_PING, envelope);
//		soapedString = (SoapPrimitive)envelope.getResponse();
//
//		return soapedString.toString();		
//	}
//
//	public Ticket getTicketFromSoap(SoapObject soapObject){
//		Ticket ticket = null;
//
//		ticket = new Ticket();
//
//		//				if(soapObject.hasProperty("causeTypeId"))
//		//					ticket.setCauseTypeId(soapObject.getProperty("causeTypeId").toString());
//		if(soapObject.hasProperty("clientId"))
//			ticket.setUserID(Integer.valueOf(soapObject.getProperty("clientId").toString()));
//		//				if(soapObject.hasProperty("contactTypeId"))
//		//					ticket.setContactTypeId(Integer.valueOf(soapObject.getProperty("contactTypeId").toString()));
//		if(soapObject.hasProperty("description"))
//			ticket.setDescription(soapObject.getProperty("description").toString());
//		if(soapObject.hasProperty("elementId"))
//			ticket.setElementId(Integer.valueOf(soapObject.getProperty("elementId").toString()));
//		//				if(soapObject.hasProperty("elementName"))
//		//					ticket.setElementName(soapObject.getProperty("elementName").toString());
//		//				if(soapObject.hasProperty("endDate"))
//		//					ticket.setEndDate(soapObject.getProperty("endDate").toString());
//		if(soapObject.hasProperty("impactId"))
//			ticket.setImpactID(Integer.valueOf(soapObject.getProperty("impactId").toString()));
//		if(soapObject.hasProperty("impactName"))
//			ticket.setImpactName(soapObject.getProperty("impactName").toString());
//		if(soapObject.hasProperty("id"))
//			ticket.setID(Integer.valueOf(soapObject.getProperty("id").toString()));
//		//				if(soapObject.hasProperty("originInSupport"))
//		//					ticket.setOriginInSupport(Boolean.valueOf(soapObject.getProperty("originInSupport").toString()));
//		//				if(soapObject.hasProperty("originUserId"))
//		//					ticket.setOriginUserId(Integer.valueOf(soapObject.getProperty("originUserId").toString()));
//		if(soapObject.hasProperty("originUsername"))
//			ticket.setOriginInName(soapObject.getProperty("originUsername").toString());
//		//				if(soapObject.hasProperty("readableId"))
//		//					ticket.setReadableId(Integer.valueOf(soapObject.getProperty("readableId").toString()));
//		//				if(soapObject.hasProperty("resolutionDate"))
//		//					ticket.setResolutionDate(soapObject.getProperty("resolutionDate").toString());
//		//				if(soapObject.hasProperty("resolutionDeadLine"))
//		//					ticket.setResolutionDeadLine(soapObject.getProperty("resolutionDeadLine").toString());
//		//				if(soapObject.hasProperty("responseDeadline"))
//		//					ticket.setResponseDeadline(soapObject.getProperty("responseDeadline").toString());
//		if(soapObject.hasProperty("responsibilityId"))
//			ticket.setAssignedToID(Integer.valueOf(soapObject.getProperty("responsibilityId").toString()));
//		if(soapObject.hasProperty("responsibilityValue"))
//			ticket.setAssignedTo(soapObject.getProperty("responsibilityValue").toString());
//		//				if(soapObject.hasProperty("serviceId"))
//		//					ticket.setServiceId(Integer.valueOf(soapObject.getProperty("serviceId").toString()));
//		//				if(soapObject.hasProperty("serviceName"))
//		//					ticket.setServiceName(soapObject.getProperty("serviceName").toString());
//		//				if(soapObject.hasProperty("solution"))
//		//					ticket.setSolution(soapObject.getProperty("solution").toString());
//		if(soapObject.hasProperty("startDate"))
//			ticket.setStartDate(soapObject.getProperty("startDate").toString());
//		//				if(soapObject.hasProperty("startWorkdate"))
//		//					ticket.setStartWorkdate(soapObject.getProperty("startWorkdate").toString());
//		if(soapObject.hasProperty("statusId"))
//			ticket.setAssignedToID((Integer.valueOf(soapObject.getProperty("statusId").toString())));
//		if(soapObject.hasProperty("statusName"))
//			ticket.setStatusName(soapObject.getProperty("statusName").toString());
//		if(soapObject.hasProperty("summary"))
//			ticket.setSummary(soapObject.getProperty("summary").toString());
//		if(soapObject.hasProperty("typeId"))
//			ticket.setTypeID(Integer.valueOf(soapObject.getProperty("typeId").toString()));
//		if(soapObject.hasProperty("typeName"))
//			ticket.setTypeName(soapObject.getProperty("typeName").toString());
//		if(soapObject.hasProperty("urgencyId"))
//			ticket.setUrgencyID(Integer.valueOf(soapObject.getProperty("urgencyId").toString()));
//		if(soapObject.hasProperty("urgencyName"))
//			ticket.setUrgencyName(soapObject.getProperty("urgencyName").toString());
//
//		return ticket;
//	}
//
//
//	public Comment getCommentFromSoap(SoapObject soapObject){
//		Comment comment = null;
//
//		comment = new Comment();
//
//		if(soapObject.hasProperty("content"))
//			comment.setContent((soapObject.getProperty("content").toString()));
//		if(soapObject.hasProperty("date"))
//			comment.setDate((soapObject.getProperty("date").toString()));
//		if(soapObject.hasProperty("isTechnicalComment"))
//			comment.setTecnical((soapObject.getProperty("isTechnicalComment").toString()));
//		if(soapObject.hasProperty("ticketId"))
//			comment.setTicketId(Integer.valueOf((soapObject.getProperty("ticketId").toString())));
//		if(soapObject.hasProperty("ticketSummary"))
//			comment.setSummary((soapObject.getProperty("ticketSummary").toString()));
//		if(soapObject.hasProperty("userName"))
//			comment.setUser((soapObject.getProperty("userName").toString()));
//
//		return comment;
//	}
//
//	public List<Ticket> getTicketsFromSoapList(Vector vector){
//
//		List<Ticket> ticketList = new ArrayList<Ticket>();
//
//		for (Object object : vector) {
//			ticketList.add(getTicketFromSoap((SoapObject)object));
//		}	 		
//		return ticketList;
//	}
//
//	public List<Comment> getCommentsFromSoapList(Vector vector){
//
//		List<Comment> commentList = new ArrayList<Comment>();
//
//		for (Object object : vector) {
//			commentList.add(getCommentFromSoap((SoapObject)object));
//		}	 		
//		return commentList;
//	}
//
//	public List<Comment> getTicketComments(int iD) throws Exception{
//
//		Vector soapedComment = null;
//
//		SoapObject request = new SoapObject(namespace, METHOD_GET_COMMENTS_FOR_TICKET);
//		PropertyInfo propertySessionKey = new PropertyInfo();
//		PropertyInfo propertyTicketId = new PropertyInfo();
//
//		propertySessionKey.setType(String.class);
//		propertySessionKey.setName("sessionKey");
//		propertySessionKey.setValue(this.sessionKey);
//		request.addProperty(propertySessionKey);
//
//		propertyTicketId.setType(int.class);
//		propertyTicketId.setName("ticketId");
//		propertyTicketId.setValue(iD);
//		request.addProperty(propertyTicketId);
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
//		envelope.dotNet = false;
//		envelope.setOutputSoapObject(request);
//
//		HttpTransportSE httpRequest = new HttpTransportSE(url);
//
//		httpRequest.call(namespace+METHOD_GET_COMMENTS_FOR_TICKET, envelope);
//		soapedComment = (Vector)envelope.getResponse();
//
//		return getCommentsFromSoapList(soapedComment);	
//
//	}
//
//	public Ticket getTicketForSupporter(int id) throws Exception{
//
//		SoapObject request = new SoapObject(namespace, METHOD_GET_TICKET_BY_ID);
//
//		PropertyInfo propertySessionKey = new PropertyInfo();
//		propertySessionKey.setType(String.class);
//		propertySessionKey.setName("sessionKey");
//		propertySessionKey.setValue(this.sessionKey);
//		request.addProperty(propertySessionKey);
//
//		PropertyInfo autenticationPropertyTicketId = new PropertyInfo();
//		autenticationPropertyTicketId.setType(int.class);
//		autenticationPropertyTicketId.setName("ticketId");
//		autenticationPropertyTicketId.setValue(id);
//		request.addProperty(autenticationPropertyTicketId);
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
//		envelope.dotNet = false;
//		envelope.setOutputSoapObject(request);
//
//		HttpTransportSE httpRequest = new HttpTransportSE(url);
//		httpRequest.call(namespace+METHOD_GET_TICKET_BY_ID, envelope);
//		SoapObject response = (SoapObject)envelope.getResponse();
//
//
//		return getTicketFromSoap(response);
//
//	}
//	
//	 	public List<Comment> getTicketCommentsNeedingMyAttention() throws Exception{
//
//		 	Vector soapedComment = null;
//
//		    SoapObject request = new SoapObject(namespace, METHOD_GET_TICKET_COMMENTS_NEEDING_ATTENTION);
//		    PropertyInfo propertySessionKey = new PropertyInfo();
//
//		    propertySessionKey.setType(String.class);
//		    propertySessionKey.setName("sessionKey");
//		    propertySessionKey.setValue(this.sessionKey);
//		    request.addProperty(propertySessionKey);
//
//	        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
//	        envelope.dotNet = false;
//	        envelope.setOutputSoapObject(request);
//
//	        HttpTransportSE httpRequest = new HttpTransportSE(url);
//
//	        httpRequest.call(namespace+METHOD_GET_TICKET_COMMENTS_NEEDING_ATTENTION, envelope);
//	        soapedComment = (Vector)envelope.getResponse();
//
//	        return getCommentsFromSoapList(soapedComment);	
//
//	 	}
//
//
//	public void mostraInfo(int id) throws Exception{
//
//		Vector soapedTicketStatus = null;
//
//		SoapObject request = new SoapObject(namespace, "getServiceClientEndUsers");
//		PropertyInfo propertySessionKey = new PropertyInfo();
//		PropertyInfo propertyId = new PropertyInfo();
//
//		propertySessionKey.setType(String.class);
//		propertySessionKey.setName("sessionKey");
//		propertySessionKey.setValue(this.sessionKey);
//		request.addProperty(propertySessionKey);
//		/*
//		    propertyId.setType(Integer.class);
//		    propertyId.setName("ticketId");
//		    propertyId.setValue(id);
//		    request.addProperty(propertyId);
//		 */
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
//		envelope.dotNet = false;
//		envelope.setOutputSoapObject(request);
//
//		HttpTransportSE httpRequest = new HttpTransportSE(url);
//
//		httpRequest.call(namespace+"getServiceClientEndUsers", envelope);
//		soapedTicketStatus = (Vector<?>)envelope.getResponse();
//
//
//		TestClass objectAux;
//		for (Object object : soapedTicketStatus) {
//			objectAux=(getTestClassFromSoap((SoapObject)object));
//			System.out.println("\n------------------------------------\n");
//			System.out.println(objectAux.getId() + " agora o nome   ");
//			System.out.println(objectAux.getName());
//			System.out.println("\n------------------------------------\n");
//		}	 		
//
//	}
//
//	public TestClass getTestClassFromSoap(SoapObject elem){
//
//		TestClass teste = new TestClass();
//
//		if(elem.hasProperty("name"))
//			teste.setName((elem.getProperty("name").toString()));
//		if(elem.hasProperty("id"))
//			teste.setId(Integer.valueOf(elem.getProperty("id").toString()));
//
//		return teste;
//	}
//
//	public Ticket createTicketBySupporter(int serviceId,
//			int urgencyId, int impactId, int ticketTypeId, 
//			java.lang.String summary, java.lang.String description, 
//			java.lang.Boolean originInSupport, java.lang.Integer contactTypeId,
//			java.lang.Integer clientId, java.lang.Integer originUserId) throws Exception{
//
//		//	 		java.lang.String sessionKey, int serviceId,
//		// 			int urgencyId, int impactId, int ticketTypeId, 
//		// 			java.lang.String summary, java.lang.String description, 
//		// 			java.lang.Boolean originInSupport, java.lang.Integer contactTypeId,
//		// 			java.lang.Integer clientId, java.lang.Integer originUserId
//
//		SoapObject request = new SoapObject(namespace, METHOD_CREATE_TICKET_BY_SUPPORTER);
//		PropertyInfo propertyInfoSessionKey = new PropertyInfo();
//		PropertyInfo propertyInfoServiceId = new PropertyInfo();
//		PropertyInfo propertyInfoUrgencyId = new PropertyInfo();
//		PropertyInfo propertyInfoImpactId = new PropertyInfo();
//		PropertyInfo propertyInfoTicketTypeId = new PropertyInfo();
//		PropertyInfo propertyInfoSummary = new PropertyInfo();
//		PropertyInfo propertyInfoDescription = new PropertyInfo();
//		PropertyInfo propertyInfoOriginInSupport = new PropertyInfo();
//		PropertyInfo propertyInfoContactTypeId = new PropertyInfo();
//		PropertyInfo propertyInfoClientId = new PropertyInfo();
//		PropertyInfo propertyInfoOriginUserId = new PropertyInfo();
//
//
//		propertyInfoSessionKey.setType(String.class);
//		propertyInfoSessionKey.setName("sessionKey");
//		propertyInfoSessionKey.setValue(this.sessionKey);
//		request.addProperty(propertyInfoSessionKey);
//
//		propertyInfoServiceId.setType(int.class);
//		propertyInfoServiceId.setName("serviceId");
//		propertyInfoServiceId.setValue(serviceId);
//		request.addProperty(propertyInfoServiceId);
//
//		propertyInfoUrgencyId.setType(int.class);
//		propertyInfoUrgencyId.setName("urgencyId");
//		propertyInfoUrgencyId.setValue(urgencyId);
//		request.addProperty(propertyInfoUrgencyId);
//
//		propertyInfoImpactId.setType(int.class);
//		propertyInfoImpactId.setName("impactId");
//		propertyInfoImpactId.setValue(impactId);
//		request.addProperty(propertyInfoImpactId);
//
//		propertyInfoTicketTypeId.setType(int.class);
//		propertyInfoTicketTypeId.setName("ticketTypeId");
//		propertyInfoTicketTypeId.setValue(ticketTypeId);
//		request.addProperty(propertyInfoTicketTypeId);
//
//		propertyInfoSummary.setType(String.class);
//		propertyInfoSummary.setName("summary");
//		propertyInfoSummary.setValue(summary);
//		request.addProperty(propertyInfoSummary);
//
//		propertyInfoDescription.setType(String.class);
//		propertyInfoDescription.setName("description");
//		propertyInfoDescription.setValue(description);
//		request.addProperty(propertyInfoDescription);
//
//		propertyInfoOriginInSupport.setType(Boolean.class);
//		propertyInfoOriginInSupport.setName("originInSupport");
//		propertyInfoOriginInSupport.setValue(originInSupport);
//		request.addProperty(propertyInfoOriginInSupport);
//
//		propertyInfoContactTypeId.setType(Integer.class);
//		propertyInfoContactTypeId.setName("contactTypeId");
//		propertyInfoContactTypeId.setValue(contactTypeId);
//		request.addProperty(propertyInfoContactTypeId);
//
//		propertyInfoClientId.setType(Integer.class);
//		propertyInfoClientId.setName("clientId");
//		propertyInfoClientId.setValue(clientId);
//		request.addProperty(propertyInfoClientId);
//
//		propertyInfoOriginUserId.setType(Integer.class);
//		propertyInfoOriginUserId.setName("originUserId");
//		propertyInfoOriginUserId.setValue(originUserId);
//		request.addProperty(propertyInfoOriginUserId);
//
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
//		envelope.dotNet = false;
//		envelope.setOutputSoapObject(request);
//
//		HttpTransportSE httpRequest = new HttpTransportSE(url);
//
//		httpRequest.call(namespace+METHOD_CREATE_TICKET_BY_SUPPORTER, envelope);
//		SoapObject response = (SoapObject)envelope.getResponse();
//
//
//		return getTicketFromSoap(response);
//	}
//
//
//	public void createTicketCommentBySupporter(
//			int ticketId,
//			java.lang.String comment,
//			boolean isTechnical,
//			int worktime) throws Exception
//			{
//		SoapObject request = new SoapObject(namespace, METHOD_CREATE_TICKET_COMMENT);
//		PropertyInfo propertyInfoSessionKey = new PropertyInfo();
//		PropertyInfo propertyInfoTicketId = new PropertyInfo();
//		PropertyInfo propertyInfoComment = new PropertyInfo();
//		PropertyInfo propertyInfoIsTechnical = new PropertyInfo();
//		PropertyInfo propertyInfoWorktime = new PropertyInfo();
//
//		propertyInfoSessionKey.setType(String.class);
//		propertyInfoSessionKey.setName("sessionKey");
//		propertyInfoSessionKey.setValue(this.sessionKey);
//		request.addProperty(propertyInfoSessionKey);
//
//		propertyInfoTicketId.setType(int.class);
//		propertyInfoTicketId.setName("ticketId");
//		propertyInfoTicketId.setValue(ticketId);
//		request.addProperty(propertyInfoTicketId);
//
//		propertyInfoComment.setType(String.class);
//		propertyInfoComment.setName("comment");
//		propertyInfoComment.setValue(comment);
//		request.addProperty(propertyInfoComment);
//
//		propertyInfoIsTechnical.setType(Boolean.class);
//		propertyInfoIsTechnical.setName("isTechnical");
//		propertyInfoIsTechnical.setValue(isTechnical);
//		request.addProperty(propertyInfoIsTechnical);
//
//		propertyInfoWorktime.setType(int.class);
//		propertyInfoWorktime.setName("worktime");
//		propertyInfoWorktime.setValue(worktime);
//		request.addProperty(propertyInfoWorktime);
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
//		envelope.dotNet = false;
//		envelope.setOutputSoapObject(request);
//
//		HttpTransportSE httpRequest = new HttpTransportSE(url);
//
//		httpRequest.call(namespace+METHOD_CREATE_TICKET_COMMENT, envelope);
//		//SoapObject response = (SoapObject)envelope.getResponse();
//			}
//
//	public Ticket modifyTicketBySupporter(
//			int serviceId,
//			int ticketId,
//			int urgencyId,
//			int impactId,
//			int ticketTypeId,
//			java.lang.String summary,
//			java.lang.String description,
//			java.lang.String startDate,
//			java.lang.Integer statusId,
//			java.lang.Boolean originInSupport,
//			java.lang.Integer contactTypeId,
//			java.lang.Integer clientId,
//			java.lang.Integer originUserId,
//			java.lang.Integer elementId,
//			java.lang.String attribDate,
//			java.lang.String startWorkDate,
//			java.lang.String endDate,
//			java.lang.String causeTypeId,
//			java.lang.Integer responsabilityId) throws Exception
//			{
//
//
//		SoapObject request = new SoapObject(namespace, METHOD_EDIT_TICKET_BY_SUPPORTER);
//		PropertyInfo propertyInfoSessionKey = new PropertyInfo();
//		PropertyInfo propertyInfoTicketId = new PropertyInfo();
//		PropertyInfo propertyInfoServiceId = new PropertyInfo();
//		PropertyInfo propertyInfoUrgencyId = new PropertyInfo();
//		PropertyInfo propertyInfoImpactId = new PropertyInfo();
//		PropertyInfo propertyInfoTicketTypeId = new PropertyInfo();
//		PropertyInfo propertyInfoSummary = new PropertyInfo();
//		PropertyInfo propertyInfoDescription = new PropertyInfo();
//		PropertyInfo propertyInfoOriginInSupport = new PropertyInfo();
//		PropertyInfo propertyInfoContactTypeId = new PropertyInfo();
//		PropertyInfo propertyInfoClientId = new PropertyInfo();
//		PropertyInfo propertyInfoOriginUserId = new PropertyInfo();
//		PropertyInfo propertyStartDate = new PropertyInfo();
//		PropertyInfo propertyAttribDate = new PropertyInfo();
//		PropertyInfo propertyStartWorkDate = new PropertyInfo();
//		PropertyInfo propertyEndDate = new PropertyInfo();
//		PropertyInfo propertyCauseTypeId = new PropertyInfo();
//		PropertyInfo propertyElementId = new PropertyInfo();
//		PropertyInfo propertyResponsabilityId = new PropertyInfo();
//
//		propertyInfoSessionKey.setType(String.class);
//		propertyInfoSessionKey.setName("sessionKey");
//		propertyInfoSessionKey.setValue(this.sessionKey);
//		request.addProperty(propertyInfoSessionKey);
//
//		propertyInfoTicketId.setType(int.class);
//		propertyInfoTicketId.setName("ticketId");
//		propertyInfoTicketId.setValue(ticketId);
//		request.addProperty(propertyInfoTicketId);
//
//		propertyInfoServiceId.setType(int.class);
//		propertyInfoServiceId.setName("serviceId");
//		propertyInfoServiceId.setValue(serviceId);
//		request.addProperty(propertyInfoServiceId);
//
//		propertyInfoUrgencyId.setType(int.class);
//		propertyInfoUrgencyId.setName("urgencyId");
//		propertyInfoUrgencyId.setValue(urgencyId);
//		request.addProperty(propertyInfoUrgencyId);
//
//		propertyInfoImpactId.setType(int.class);
//		propertyInfoImpactId.setName("impactId");
//		propertyInfoImpactId.setValue(impactId);
//		request.addProperty(propertyInfoImpactId);
//
//		propertyInfoTicketTypeId.setType(int.class);
//		propertyInfoTicketTypeId.setName("ticketTypeId");
//		propertyInfoTicketTypeId.setValue(ticketTypeId);
//		request.addProperty(propertyInfoTicketTypeId);
//
//		propertyInfoSummary.setType(String.class);
//		propertyInfoSummary.setName("summary");
//		propertyInfoSummary.setValue(summary);
//		request.addProperty(propertyInfoSummary);
//
//		propertyInfoDescription.setType(String.class);
//		propertyInfoDescription.setName("description");
//		propertyInfoDescription.setValue(description);
//		request.addProperty(propertyInfoDescription);
//
//		propertyInfoOriginInSupport.setType(Boolean.class);
//		propertyInfoOriginInSupport.setName("originInSupport");
//		propertyInfoOriginInSupport.setValue(originInSupport);
//		request.addProperty(propertyInfoOriginInSupport);
//
//		propertyInfoContactTypeId.setType(Integer.class);
//		propertyInfoContactTypeId.setName("contactTypeId");
//		propertyInfoContactTypeId.setValue(contactTypeId);
//		request.addProperty(propertyInfoContactTypeId);
//
//		propertyInfoClientId.setType(Integer.class);
//		propertyInfoClientId.setName("clientId");
//		propertyInfoClientId.setValue(clientId);
//		request.addProperty(propertyInfoClientId);
//
//		propertyInfoOriginUserId.setType(Integer.class);
//		propertyInfoOriginUserId.setName("originUserId");
//		propertyInfoOriginUserId.setValue(originUserId);
//		request.addProperty(propertyInfoOriginUserId);
//
//		propertyElementId.setType(Integer.class);
//		propertyElementId.setName("elementId");
//		propertyElementId.setValue(elementId);
//		request.addProperty(propertyElementId);
//
//		propertyStartDate.setType(String.class);
//		propertyStartDate.setName("startDate");
//		propertyStartDate.setValue(startDate);
//		request.addProperty(propertyStartDate);
//
//		propertyAttribDate.setType(String.class);
//		propertyAttribDate.setName("attribDate");
//		propertyAttribDate.setValue(startDate);
//		request.addProperty(propertyAttribDate);
//
//		propertyStartWorkDate.setType(String.class);
//		propertyStartWorkDate.setName("startWorkDate");
//		propertyStartWorkDate.setValue(startDate);
//		request.addProperty(propertyStartWorkDate);
//
//		propertyEndDate.setType(String.class);
//		propertyEndDate.setName("endDate");
//		propertyEndDate.setValue(endDate);
//		request.addProperty(propertyEndDate);
//
//		propertyCauseTypeId.setType(String.class);
//		propertyCauseTypeId.setName("causeTypeId");
//		propertyCauseTypeId.setValue(causeTypeId);
//		request.addProperty(propertyCauseTypeId);
//
//		propertyResponsabilityId.setType(Integer.class);
//		propertyResponsabilityId.setName("responsabilityId");
//		propertyResponsabilityId.setValue(responsabilityId);
//		request.addProperty(propertyResponsabilityId);
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
//		envelope.dotNet = false;
//		envelope.setOutputSoapObject(request);
//
//		HttpTransportSE httpRequest = new HttpTransportSE(url);
//
//		httpRequest.call(namespace+METHOD_EDIT_TICKET_BY_SUPPORTER, envelope);
//		SoapObject response = (SoapObject)envelope.getResponse();
//
//		return getTicketFromSoap(request);
//			}
//	
//	



}
