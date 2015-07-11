package com.javaConnect.auth.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.Location;

public class User {
	
	
	private int id ;
	private String username;
	private String email;
	private Long contact;
	private String address;
	private float ssc;
	private float hsc;
	private float deg;
	private float avg;
	private String skills[];
	private String location[];
	private String sk;
	private String loc;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getContact() {
		return contact;
	}
	public void setContact(Long contact) {
		this.contact = contact;
	}
	public float getSsc() {
		return ssc;
	}
	public void setSsc(float ssc) {
		this.ssc = ssc;
	}
	public float getHsc() {
		return hsc;
	}
	public void setHsc(float hsc) {
		this.hsc = hsc;
	}
	public float getDeg() {
		return deg;
	}
	public void setDeg(float deg) {
		this.deg = deg;
	}
	public float getAvg() {
		return avg;
	}
	public void setAvg(float avg) {
		this.avg = avg;
	}
	public String[] getSkills() {
		return skills;
	}
	public void setSkills(String[] skills) {
		this.skills = skills;
	}
	public String[] getLocation() {
		return location;
	}
	public void setLocation(String[] location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	public static boolean insertUser(User user, Connection conn){
		 String sql = "INSERT INTO users (username, email, contact, address, ssc, hsc, deg, avg) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		 try{
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = null;
				Statement stmt= null;
			 	pstmt.setString(1, user.getUsername());
			 	pstmt.setString(2, user.getEmail());
			 	pstmt.setLong(3, user.getContact());
			 	pstmt.setString(4, user.getAddress());
			 	pstmt.setFloat(5, user.getSsc());
			 	pstmt.setFloat(6, user.getHsc());
			 	pstmt.setFloat(7, user.getDeg());
			 	pstmt.setFloat(8, user.getAvg());

			 	int rows = pstmt.executeUpdate();
			 	sql = "select id from users where email = ?";
			 	pstmt = conn.prepareStatement(sql);
			 	pstmt.setString(1, user.getEmail());
			 	rs = pstmt.executeQuery();
			 	if(rs.next()){
			 		user.setId(rs.getInt(1));
			 	}
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		 return true;
		 
	}
	
	public static boolean insertSkills(User user, Connection conn){
		String skills[] = user.getSkills();
		String sql = "INSERT INTO skills (id , skill) VALUES (?, ?)";
		for (int i= 0 ; i< skills.length;  i++){
		 try{
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getId());
			pstmt.setString(2, skills[i]);
		 	int rows = pstmt.executeUpdate();
		 	pstmt =null;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		}
		 return true;
		 
	}
	
	public String getSk() {
		return sk;
	}
	public void setSk(String sk) {
		this.sk = sk;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public static boolean insertLocations(User user, Connection conn){
		String locations[] = user.getLocation();
		for (int i= 0 ; i< locations.length; i++){
		 String sql = "INSERT INTO locations (id , location) VALUES (?, ?)";
		 try{
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, user.getId());
				pstmt.setString(2, locations[i]);
			 	int rows = pstmt.executeUpdate();
			 	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		}
		 return true;
		 
	}
	
	public static boolean validateEmail(String email, Connection conn){
		 String sql = "select email from users where email = ? ";
		 ResultSet rs = null;
		 PreparedStatement pstmt=null;
		 try  {
			
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, email);
			 rs = pstmt.executeQuery();
			 if(rs.next()){
				 return false;
			 }
			 else 
				 return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		 
		 finally{
			 try {
				 if(rs != null && pstmt != null){
					 rs.close();
					 pstmt.close();
				 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 }
	}
	
	
	public static ArrayList<User> searchUser(User user, Connection conn) {
		String skills[] = user.getSkills();
		String loactions[] = user.getLocation();
		ArrayList<User> userList =  new ArrayList<User>();
		User u ;
		int i=0;
		int len ;
		int j=0;
		StringBuilder sql = new StringBuilder();
		
		sql.append( "select distinct u.username, u.contact, u.email,  u.avg , GROUP_CONCAT(DISTINCT (s.skill)) AS skills , GROUP_CONCAT(DISTINCT (l.location) ) AS locstion from users as  u "
				+ "join skills as s on s.id = u.id "
				+ "join locations as l on l.id = u.id "
				+ "where u.deg > ? and  s.skill  = any (select skill from skills where id in (u.id) and skill in  ( ");
		for (i=0; i<skills.length-1; i++){
			sql.append("?, ");
		}
		sql.append("? )) and l.location = any  (select location from locations where id in ( u.id) and location in (");
		for(i=0; i<loactions.length-1; i++){
			sql.append("?, ");
		}
		sql.append("? )) group by u.id");

		System.out.println(sql);
		 try{
			 Statement stmt = conn.createStatement();
			 stmt.execute("SET GLOBAL group_concat_max_len=512;");
			 PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			 ResultSet rs = null;	
			 pstmt.setFloat(1, user.getDeg());
			 for (i=2, j=0 ; j < skills.length; i++, j++){
				 pstmt.setString(i, skills[j]);
			 }
			 System.out.println("i: "+ i);
			 System.out.println("lenth: "+ loactions.length);
			 for ( j=0 ; j < loactions.length; i++, j++){
				 pstmt.setString(i, loactions[j]);
			 }

			 	rs = pstmt.executeQuery();
			 	System.out.println(rs.getStatement().toString());
			 	//System.out.println("Result "+ rs.next());
			 	while(rs.next()){
			 		u =	new User();
			 		u.setUsername(rs.getString(1));
			 		System.out.println("username: "+u.getUsername());
			 		u.setContact(rs.getLong(2));
			 		u.setEmail(rs.getString(3));
			 		u.setAvg(rs.getFloat(4));
			 		System.out.println("The skill ");
			 		u.setSk(rs.getString(5));
			 		u.setLoc(rs.getString(6));
			 		userList.add(u);
 			 	}
			 	
			 	System.out.println(userList.size());
			 	return userList;
			 	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		 
		
	}
	/*public static User authenticate(User user, Connection conn){
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		try {
			pstmt = conn.prepareStatement("select id, username, email, location, about_me, role_id from users where email = ? and password = ?");
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()){
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setLocation(rs.getString(4));
				user.setAbout(rs.getString(5));
				user.setRoleId(rs.getInt(6));
				return user;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				pstmt.close();
				if(rs!=null)
				rs.close();			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	

		
		public static boolean insertUser(User user, Connection conn){
			 String sql = "INSERT INTO users (username, password, email, about_me, last_seen, member_since, profile_pic, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
			 String date = sdf.format(new Date());
			 try{
					PreparedStatement pstmt = conn.prepareStatement(sql);
				 	pstmt.setString(1, user.getUsername());
				 	pstmt.setString(2, user.getPassword());
				 	pstmt.setString(3, user.getEmail());
				 	pstmt.setString(4, user.getAbout());
				 	pstmt.setString(5, date);
				 	pstmt.setString(6, date);
				 	if (user.getProfilePic() != null)
				 		pstmt.setBlob(7, user.getProfilePic());
				 	pstmt.setString(8, user.getLocation());
				 	int rows = pstmt.executeUpdate();
				 	if (rows> 0){
				 		return true;
				 	}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			 return true;
			 
		}
	

		public static User getProfile(User user, Connection conn){
			PreparedStatement pstmt = null;
			ResultSet rs= null;
			String sql = "select  username, email, about_me, last_seen, location, member_since, id, count(*) as followers , q.following from users "
					+ "left join follows as f on f.followed_id = id  "
					+ "join (select   count(*) as following  from users "
					+ "join follows as ff on ff.follower_id = id where username = ? ) as q where username = ? ";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getUsername());
				pstmt.setString(2, user.getUsername());
				rs = pstmt.executeQuery();
				if(rs.next()){
					user.setUsername(rs.getString(1));
					user.setEmail(rs.getString(2));
					user.setAbout(rs.getString(3));
					user.setLast_seen(rs.getString(4));
					user.setLocation(rs.getString(5));
					user.setMember_since(rs.getString(6));
					user.setId(rs.getInt(7));
					user.setFollowerCount(rs.getInt(8));
					user.setFollowedCount(rs.getInt(9));
					return user;
				}
				return null;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			finally{
				try {
					if(rs != null && pstmt != null){
						 rs.close();
						 pstmt.close();
					 }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static ArrayList<User> getUserList(Connection conn){
			Statement stmt = null;
			ResultSet rs = null;
			User user ;
			ArrayList<User> userList = new ArrayList<User>();
			try {
				stmt = conn.createStatement();
				rs  = stmt.executeQuery("select u.username, u.email, u.about_me, u.last_seen, u.location, u.member_since, IFNULL(COUNT(p.author_id), 0) as postCount from users as u left join (select author_id from posts) as p on p.author_id = u.id group by u.id order by postCount desc");
				while(rs.next()){
					user = new User();
					user.setUsername(rs.getString(1));
					user.setEmail(rs.getString(2));
					user.setAbout(rs.getString(3));
					user.setLast_seen(rs.getString(4));
					user.setLocation(rs.getString(5));
					user.setMember_since(rs.getString(6));
					user.setPostCount(rs.getInt(7));
					userList.add(user);
					
				}
				return userList;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		public int getRoleId() {
			return roleId;
		}
		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}
		
		public static boolean editProfile(User user, Connection conn){
			PreparedStatement pstmt = null;
			String sql = "update users set username = ?, email = ?, password  = ?, about_me = ?, location = ?, profile_pic = ? where password= ? and username = ?";
			int rows=0;
			try {
				System.out.println("old passwd: " + user.getOldPassword());
				System.out.println("new username" + user.getOldUsername());
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getUsername());
				pstmt.setString(2, user.getEmail());
				pstmt.setString(3, user.getPassword());
				pstmt.setString(4, user.getAbout());
				pstmt.setString(5, user.getLocation());
				if (user.getProfilePic() != null)
					pstmt.setBlob(6, user.getProfilePic());
				pstmt.setString(7, user.getOldPassword());
				pstmt.setString(8, user.getOldUsername());
				rows = pstmt.executeUpdate();
				System.out.println("rows: "+ rows);
				if(rows > 0)
					return true;
							
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				try {
					//conn.commit();
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return false;
		}
		public int getFollowerCount() {
			return followerCount;
		}
		public void setFollowerCount(int followerCount) {
			this.followerCount = followerCount;
		}
		public int getFollowedCount() {
			return followedCount;
		}
		public void setFollowedCount(int followedCount) {
			this.followedCount = followedCount;
		}
		
		*/

}
