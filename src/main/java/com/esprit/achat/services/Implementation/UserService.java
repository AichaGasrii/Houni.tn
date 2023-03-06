package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.repositories.RoleRepository;
import com.esprit.achat.repositories.UserRepository;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userDao;

    @Autowired
    private RoleRepository roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        Role FournisseurRole = new Role();
        FournisseurRole.setRoleName("Fournisseur");
        FournisseurRole.setRoleDescription("Fournisseur role");
        roleDao.save(FournisseurRole);


        Role ModerateurRole = new Role();
        ModerateurRole.setRoleName("Moderateur");
        ModerateurRole.setRoleDescription("Moderateur role");
        roleDao.save(ModerateurRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

    public User registerNewUser(User user) {


        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));



        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public List<User> getAll(){
        return userDao.findAll();
    }
    public User findOne(String userName){
        return userDao.findById(userName).orElse(null);
    }
    public void delete(String userName) {
        User u = userDao.findById(userName).orElse(null);
        if (u != null) {
            u.getRole().clear();
            userDao.delete(u);
        }
    }
    public void update(User user){
        userDao.save(user);
    }
    //public User findByEmail(String Email) {
       // return userDao.findByEmail(Email);
   // }
    public long count(){
      long count=userDao.count();
      return count;
    }
    public long countoperateur(){
        long countoperateur=0;
        List<User> users=userDao.findAll();
        for(User user:users) {

        Set<Role> roles=user.getRole();
        Role role= roles.iterator().next();
        String rolename = role.getRoleName();
                if(rolename.equals("User")){
                    countoperateur+=1;
                    }
        }
            return countoperateur;
    }
    public long countadmin(){
        long countadmin=0;
        List<User> users=userDao.findAll();
        for(User user:users) {

            Set<Role> roles=user.getRole();
            Role role= roles.iterator().next();
            String rolename = role.getRoleName();
            if(rolename.equals("Admin")){
                countadmin+=1;
            }
        }
        return countadmin;
    }
    public long countusers(){
        long countusers=0;
        List<User> users=userDao.findAll();
        for(User user:users) {

            Set<Role> roles=user.getRole();
            Role role= roles.iterator().next();
            String rolename = role.getRoleName();
            if(rolename.equals("User")){
                countusers +=1;
            }
        }
        return countusers;
    }
  //  public boolean ifEmailExist(String email){
        //return userDao.existsByEmail(email);
  //  }
   // public User getUserByMail(String mail){
      //  return this.userDao.findByEmail(mail);
   // }




    public static final String ACCOUNT_SID = "ACa9b8ab43f7a7e83f03838cc677d4c33b";
    public static final String AUTH_TOKEN = "43fcab1438b98b24d4f913b1234c0be0";

    public static final String sender_number ="+15673131960";

    public void sms(String userName){
        User user = userDao.findById(userName).orElse(null);
        // Find your Account Sid and Token at twilio.com/user/account
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+216"+ user.getUserNumber()),
                new PhoneNumber(sender_number),
                "This is the ship that made the Kessel Run in fourteen parsecs?").create();

        System.out.println(message.getSid());
    }

    // Reset Password:
    public boolean ifEmailExist(String UserEmail){
        return userDao.existsByUserEmail(UserEmail);
    }

    @Transactional
    public String getPasswordByUserEmail(String userEmail){
        return userDao.getPasswordByUserEmail(userEmail);
    }

    public User findByUserEmail(String UserEmail)
    {
        return this.userDao.findByUserEmail(UserEmail);
    }

    public void editUser(User user){
        this.userDao.save(user);
    }




    public void addRoleToUser(String roleName, String user)
    {
        Role r = roleDao.findById(roleName).orElse(null);
        User u= userDao.findById(user).orElse(null);
        Set<Role> userRoles = u.getRole();
        userRoles.add(r);
        u.setRole(userRoles);
        userDao.save(u);
    }


    public User retrieve(String username) {
        try{
            return  userDao.findById(username).get();
        } catch (Exception err) {
            System.out.println("Un erreur est survenue : " + err);
        }
        return null;
    }


}
