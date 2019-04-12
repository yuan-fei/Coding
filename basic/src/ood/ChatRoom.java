package ood;

import java.util.Arrays;
import java.util.List;

/**
 * Assume we'll focus on the following workflows:
 * 
 * Text conversations only
 * 
 * Users
 * 
 * - Add a user
 * 
 * - Remove a user
 * 
 * - Update a user
 * 
 * - Add to a user's friends list
 * 
 * - Add friend request
 * 
 * - Approve friend request
 * 
 * - Reject friend request
 * 
 * - Remove from a user's friends list
 * 
 * - Create a group chat
 * 
 * - Invite friends to a group chat
 * 
 * - Post a message to a group chat
 * 
 * - Private 1-1 chat
 * 
 * - Invite a friend to a private chat
 * 
 * - Post a message to a private chat
 * 
 * No need to worry about scaling initially
 */
public class ChatRoom {
	List<Chat> chatList;
	List<User> userList;

	void addUser(User user) {
		userList.add(user);
	}

	void removeUser(User user) {
		userList.remove(user);
	}

	void updateUser(User user) {

	}

	Chat createPrivateChat(User user1, User user2) {
		Chat c = new PrivateChat(user1, user2);
		chatList.add(c);
		return c;
	}

	Chat createGroupChat(List<User> friends) {
		Chat c = new GroupChat(friends);
		chatList.add(c);
		return c;
	}
}

class Chat {
	int id;
	List<User> userList;

	void sendMessage(User sender, String msg) {
		for (User receiver : userList) {
			if (receiver != sender) {
				receiver.receiveMsg(this, msg, sender);
			}
		}
	}
}

class PrivateChat extends Chat {

	public PrivateChat(User user1, User user2) {
		userList = Arrays.asList(user1, user2);
	}
}

class GroupChat extends Chat {

	public GroupChat(List<User> users) {
		userList = users;
	}

	void addUser(User user) {
		userList.add(user);
	}
}

class User {
	int id;
	List<User> friendList;
	List<FriendRequest> sentfriendRequestList;
	List<FriendRequest> receivedfriendRequestList;

	Chat createPrivateChat(ChatRoom chatRoom, User friend) {
		return chatRoom.createPrivateChat(this, friend);
	}

	Chat createGroupChat(ChatRoom chatRoom, List<User> friends) {
		friends.add(this);
		return chatRoom.createGroupChat(friends);
	}

	void invite(Chat chat, User user) {

	}

	void sendMessage(Chat chat, String msg) {
		chat.sendMessage(this, msg);
	}

	void receiveMsg(Chat chat, String msg, User from) {

	}

	FriendRequest requestFriend(User user) {
		return new FriendRequest(this, user);
	}

	void receiveFriendRequest(FriendRequest request) {

	}

	void approveRequest(FriendRequest request) {
		friendList.add(request.from);
		receivedfriendRequestList.remove(request);
		request.to.requestApproved(request);
	}

	void requestApproved(FriendRequest request) {
		addFriend(request.to);
		sentfriendRequestList.remove(request);
	}

	void rejectRequest(FriendRequest request) {
		receivedfriendRequestList.remove(request);
		request.to.requestRejected(request);
	}

	void requestRejected(FriendRequest request) {
		sentfriendRequestList.remove(request);
	}

	void addFriend(User user) {
		friendList.add(user);
	}

	void removeFriend(User user) {
		friendList.remove(user);
	}

}

class FriendRequest {
	User from;
	User to;

	public FriendRequest(User from, User to) {

	}

}