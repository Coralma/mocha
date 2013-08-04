package com.coral.foundation.linkedin;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinConnectionNetworkUpdate;
import com.google.code.linkedinapi.client.AsyncLinkedInApiClient;
import com.google.code.linkedinapi.client.GroupsApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.enumeration.GroupMembershipField;
import com.google.code.linkedinapi.client.enumeration.NetworkUpdateType;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.impl.BaseLinkedInApiClient;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Company;
import com.google.code.linkedinapi.schema.Connections;
import com.google.code.linkedinapi.schema.GroupMembership;
import com.google.code.linkedinapi.schema.GroupMemberships;
import com.google.code.linkedinapi.schema.MemberGroup;
import com.google.code.linkedinapi.schema.Network;
import com.google.code.linkedinapi.schema.NetworkStats;
import com.google.code.linkedinapi.schema.NetworkUpdateReturnType;
import com.google.code.linkedinapi.schema.Person;
import com.google.code.linkedinapi.schema.Position;
import com.google.code.linkedinapi.schema.Update;
import com.google.code.linkedinapi.schema.UpdateComment;
import com.google.code.linkedinapi.schema.UpdateComments;
import com.google.code.linkedinapi.schema.Updates;

public class LinkedinImpl {

	private String consumerKeyValue = "pps9akw5t85u";
	private String consumerSecretValue = "rz2R2HpXYQoQasr2";
	private String callBackUrl = "https://www.mocha-platform.com/cooperate";
	private final LinkedInOAuthService oauthService = LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(consumerKeyValue,
			consumerSecretValue);
	private final LinkedInApiClientFactory factory = LinkedInApiClientFactory.newInstance(consumerKeyValue, consumerSecretValue);
	private Object scope;

	public LinkedinImpl(String consumerKeyValue, String consumerSecretValue, String callBackUrl) {
		this.consumerKeyValue = consumerKeyValue;
		this.consumerSecretValue = consumerSecretValue;
		this.callBackUrl = callBackUrl;
	}

	public LinkedinImpl() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedinImpl apiSample = new LinkedinImpl();
		String callbackUrl = "https://www.mocha-platform.com/cooperate";
		// LinkedInRequestToken requestToken = apiSample.getOauthService().getOAuthRequestToken();
		// LinkedInRequestToken requestToken = apiSample.getOauthService().getOAuthRequestToken(callbackUrl);
		// String authUrl = requestToken.getAuthorizationUrl();
		// System.out.println(authUrl);
		// LinkedInApiClient client = apiSample.factory.createLinkedInApiClient(token);
		// GroupsApiClient groupClient=apiSample.factory.createLinkedInApiClient(token);
		// Set<ProfileField> profileFields=EnumSet.of(
		// ProfileField.ID,
		// ProfileField.POSITIONS,
		// ProfileField.FIRST_NAME,
		// ProfileField.LAST_NAME,
		// ProfileField.SUMMARY,
		// ProfileField.LOCATION_COUNTRY,
		// ProfileField.HEADLINE,
		// ProfileField.SUMMARY,
		// ProfileField.ASSOCIATIONS);
		//
		// Set<GroupMembershipField> groupFields=EnumSet.of(
		// GroupMembershipField.GROUP,
		// GroupMembershipField.GROUP_NAME,
		// GroupMembershipField.GROUP_ID);
		//
		// Connections connects =client.getConnectionsForCurrentUser(profileFields,1,50);
		// List<Person> personList = connects.getPersonList();
		// List<LinkedinConnection> connections = new ArrayList<LinkedinConnection>();
		// for (Person p : personList) {
		// System.out.println(p.getId());
		// GroupMemberships ships=groupClient.getGroupMemberships(p.getId());
		// for(GroupMembership ship:ships.getGroupMembershipList()){
		// System.out.println("ship is: "+ship.getGroup().getName()+" Persion is: "+ship.getPerson().getFirstName());
		// }
		// }
//		LinkedInAccessToken token = new LinkedInAccessToken("ff40e54b-76b3-4c4a-b884-5086c914056e", "14f5262d-1bfc-49c7-be5f-49f57509b825");
		LinkedinImpl impl=new LinkedinImpl();
		
		LinkedInAccessToken accessToken=new LinkedInAccessToken("3357e089-f96e-4162-927b-292e3a48eac1", "1b787100-4087-4b0e-a6a2-d79f87e7de22");
		impl.getFollowedConNetworkUpdate(accessToken,null);
		// apiSample.getAllNetworkUpdate(token);
	}

	public LinkedInOAuthService getOauthService() {
		return oauthService;
	}

	public LinkedInRequestToken getLinkedInRequestToken() {
		LinkedInRequestToken requestToken = oauthService.getOAuthRequestToken(callBackUrl);
		return requestToken;
	}

	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	public LinkedInAccessToken getAccessToken(LinkedInRequestToken requestToken, String oauthVerifier) {
		LinkedInAccessToken accessToken = oauthService.getOAuthAccessToken(requestToken, oauthVerifier);
		return accessToken;
	}

	public List<LinkedinConnection> getAlUserConnects(LinkedInAccessToken accessToken) {

		LinkedInApiClient client = factory.createLinkedInApiClient(accessToken);
		GroupsApiClient gaClient = factory.createLinkedInApiClient(accessToken);
		Set<ProfileField> profileFields = EnumSet.of(ProfileField.ID, ProfileField.POSITIONS, ProfileField.FIRST_NAME, ProfileField.LAST_NAME,
				ProfileField.SUMMARY, ProfileField.LOCATION_COUNTRY, ProfileField.HEADLINE, ProfileField.PICTURE_URL, ProfileField.LOCATION,
				ProfileField.INDUSTRY, ProfileField.CURRENT_STATUS, ProfileField.PHONE_NUMBERS, ProfileField.IM_ACCOUNTS, ProfileField.TWITTER_ACCOUNTS);

		Set<GroupMembershipField> groupFields = EnumSet.of(GroupMembershipField.GROUP, GroupMembershipField.GROUP_NAME, GroupMembershipField.GROUP_ID);

		Connections connects = client.getConnectionsForCurrentUser(profileFields);
		List<Person> personList = connects.getPersonList();
		List<LinkedinConnection> connections = new ArrayList<LinkedinConnection>();
		for (Person p : personList) {
			LinkedinConnection connection = new LinkedinConnection();
			connection.setFirstName(p.getFirstName());
			connection.setLastName(p.getLastName());
			connection.setPictUrl(p.getPictureUrl());
			connection.setHeadline(p.getHeadline());
			if (p.getImAccounts() != null) {
				connection.setImAccount(p.getImAccounts().getImAccountList().get(0).toString());
			}
			connection.setSummary(p.getSummary());
			if (p.getLocation() != null) {
				connection.setLocation(p.getLocation().getName());
			}
			if (p.getLocation() != null) {
				connection.setLocationCountry(p.getLocation().getCountry().getCode());
			}
			connection.setIndustry(p.getIndustry());
			connection.setCurrentStatus(p.getCurrentStatus());
			if (p.getTwitterAccounts() != null) {
				connection.setTwitterAccount(p.getTwitterAccounts().getTwitterAccountList().get(0).getProviderAccountName());
			}
			if (p.getMemberGroups() != null) {
				System.out.println("group is: " + p.getMemberGroups());
			}

			// GroupMemberships groupMemberShips=gaClient.getGroupMemberships(p.getId());
			// if (groupMemberShips != null) {
			// if (groupMemberShips.getGroupMembershipList() != null && groupMemberShips.getGroupMembershipList().size()>0) {
			// for (GroupMembership gms : groupMemberShips.getGroupMembershipList()) {
			// LinkedinGroup lg = new LinkedinGroup();
			// lg.setName(gms.getGroup().getName());
			// connection.getLinkedinGroups().add(lg);
			// }
			// }
			// }

			if (p.getPositions() != null) {
				for (Position po : p.getPositions().getPositionList()) {
					Company company = po.getCompany();
					connection.setCompanyName(company.getName());
					break;
				}
			}
			connections.add(connection);
		}
		return connections;
	}

	public List<LinkedinConnection> getUserConnectsByIndexPage(LinkedInAccessToken accessToken, int start, int count) {
		LinkedInApiClient client = factory.createLinkedInApiClient(accessToken);
		Set<ProfileField> profileFields = EnumSet.of(ProfileField.POSITIONS, ProfileField.FIRST_NAME, ProfileField.LAST_NAME, ProfileField.SUMMARY,
				ProfileField.LOCATION_COUNTRY, ProfileField.HEADLINE, ProfileField.PICTURE_URL);
		Connections connects = client.getConnectionsForCurrentUser(profileFields, start, count);
		List<Person> personList = connects.getPersonList();
		List<LinkedinConnection> connections = new ArrayList<LinkedinConnection>();
		for (Person p : personList) {
			LinkedinConnection connection = new LinkedinConnection();
			connection.setFirstName(p.getFirstName());
			connection.setLastName(p.getLastName());
			connection.setPictUrl(p.getPictureUrl());
			
			// if(p.getPositions()!=null && p.getPositions().getPositionList()!=null){
			// connection.setCompanyName(p.getPositions().getPositionList().get(0).getCompany().getName());
			// }
			
			if (p.getPositions() != null) {
				for (Position po : p.getPositions().getPositionList()) {
					Company company = po.getCompany();
					connection.setCompanyName(company.getName());
					break;
				}
			}
			connections.add(connection);
		}
		return connections;
	}

	public Person getProfileForCurrentUser(LinkedInAccessToken accessToken) {
		LinkedInApiClient client = factory.createLinkedInApiClient(accessToken);
		Set<ProfileField> profileFields = EnumSet.of(ProfileField.POSITIONS, ProfileField.FIRST_NAME, ProfileField.LAST_NAME, ProfileField.SUMMARY,
				ProfileField.LOCATION_COUNTRY, ProfileField.HEADLINE);
		Person profile = client.getProfileForCurrentUser(profileFields);
		return profile;
	}

	@SuppressWarnings("unused")
	public List<LinkedinConnectionNetworkUpdate> getFollowedConNetworkUpdate(LinkedInAccessToken accessToken, List<LinkedinConnection> conns) {
		AsyncLinkedInApiClient client = factory.createAsyncLinkedInApiClient(accessToken);
		Set<LinkedinConnectionNetworkUpdate> updateModels = new HashSet<LinkedinConnectionNetworkUpdate>();
		Set<NetworkUpdateType> networkUpdates = EnumSet.of(
				NetworkUpdateType.JOB_UPDATE, 
				NetworkUpdateType.CONNECTION_UPDATE, 
				NetworkUpdateType.PROFILE_UPDATE,
				NetworkUpdateType.STATUS_UPDATE, 
				NetworkUpdateType.SHARED_ITEM, 
				NetworkUpdateType.GROUP_UPDATE, 
				NetworkUpdateType.EXTENDED_PROFILE_UPDATE,
				NetworkUpdateType.RECOMMENDATION_UPDATE);

		Network network;
		boolean connFindFlg = false;
		try {
			network = client.getNetworkUpdates(networkUpdates, 1, 500).get();
			Updates updates = network.getUpdates();
			NetworkStats networkStats = network.getNetworkStats();
			// System.out.println("Total updates fetched:" + network.getUpdates().getTotal());

			for (Update update : network.getUpdates().getUpdateList()) {
				if (conns == null) {
					connFindFlg = true;
				}
				else {
					for (LinkedinConnection con : conns) {
						if (con.getFirstName().equals(update.getUpdateContent().getPerson().getFirstName())
								&& con.getLastName().equals(update.getUpdateContent().getPerson().getLastName())) {
							connFindFlg = true;
							break;
						}
					}
				}
				if (connFindFlg) {
					LinkedinConnectionNetworkUpdate updateModel = new LinkedinConnectionNetworkUpdate();
					updateModel.setUpdatedKey(update.getUpdateKey());
					Person person = update.getUpdateContent().getPerson();
					StringBuilder updateMessage = new StringBuilder();
					updateModel.setFirstName(person.getFirstName());
					updateModel.setLastName(person.getLastName());
					updateModel.setTimeStamp(update.getTimestamp().toString());

					// CONN case
					if (update.getUpdateType().equals(NetworkUpdateReturnType.CONNECTION_ADDED_CONNECTIONS)) {
						updateMessage.append("has add a connection with ");
						updateMessage.append(person.getConnections().getPersonList().get(0).getFirstName());
						updateMessage.append(".");
						updateMessage.append(person.getConnections().getPersonList().get(0).getLastName());
						updateModel.setUpdateMessage(updateMessage.toString());
					}

					// STAT case
					if (update.getUpdateType().equals(NetworkUpdateReturnType.STATUS_UPDATED)) {
						updateMessage.append(update.getUpdateContent().getPerson().getCurrentStatus());
						updateModel.setUpdateMessage(updateMessage.toString());
					}

					// PROF case update profiles
					if (update.getUpdateType().equals(NetworkUpdateReturnType.CONNECTION_UPDATED_PROFILE)) {
						updateMessage.append("has got profile updated");
						updateModel.setUpdateMessage(updateMessage.toString());
					}

					if (update.getUpdateType().equals(NetworkUpdateReturnType.SHARED_ITEM)) {
						updateMessage.append(update.getUpdateContent().getPerson().getCurrentStatus());
						updateModel.setUpdateMessage(updateMessage.toString());
					}

					if (update.getUpdateType().equals(NetworkUpdateReturnType.CONNECTION_JOINED_GROUP)) {
						updateMessage.append("join a new group:  ");
						if (update.getUpdateContent().getPerson().getMemberGroups() != null) {
							updateMessage.append(update.getUpdateContent().getPerson().getMemberGroups().getMemberGroupList().get(0).getName());
						}
						updateModel.setUpdateMessage(updateMessage.toString());
					}

					if (update.getUpdateType().equals(NetworkUpdateReturnType.NEW_CONNECTIONS)) {
						updateMessage.append(person.getConnections().getPersonList().get(0).getFirstName());
						updateMessage.append(".");
						updateMessage.append(person.getConnections().getPersonList().get(0).getLastName());
						updateMessage.append("has add a connection with " + person.getFirstName() + "." + person.getLastName());
						updateModel.setUpdateMessage(updateMessage.toString());
					}

					if (!checkDuplidateUpdates(updateModels, updateModel)) {
						updateModels.add(updateModel);
					}
				}
			}
			List<LinkedinConnectionNetworkUpdate> models = new ArrayList<LinkedinConnectionNetworkUpdate>();
			models.addAll(updateModels);
			return models;
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (ExecutionException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}

	// false means no duplicate found
	private boolean checkDuplidateUpdates(Set<LinkedinConnectionNetworkUpdate> updateModels, LinkedinConnectionNetworkUpdate updateModel) {
		// init the updateModels first
		if (updateModels.size() < 1) {
			return false;
		}

		for (LinkedinConnectionNetworkUpdate m : updateModels) {
			if (m.getFirstName().trim().equals(updateModel.getFirstName().trim())) {
				if (m.getLastName().trim().equals(updateModel.getLastName().trim())) {
					if (m.getUpdatedKey().trim().equals(updateModel.getUpdatedKey().trim())) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
