package com.app.service;

import com.app.enties.Customer;
import com.app.enties.Merchant;
import com.app.enties.Role;
import com.app.enties.SecurityAnswers;
import com.app.enties.SecurityQuestions;
import com.app.enties.UserType;
import com.app.enties.Users;
import com.app.repository.CustomerRepository;
import com.app.repository.MerchantRepository;
import com.app.repository.RoleRepository;
import com.app.repository.SecurityAnswersRepository;
import com.app.repository.SecurityQuestionRepository;
import com.app.repository.UsersRepository;
import com.app.request.CreateUserRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Created by Pasindu on 6/29/17. */
@Service
public class UserRegistration {
  private static final Logger logger = LoggerFactory.getLogger(UserRegistration.class);
  @Autowired UserServiceImpl userService;
  @Autowired UsersRepository usersRepository;
  @Autowired CustomerRepository customerRepository;
  @Autowired
  SecurityAnswersRepository securityAnswersRepository;
  @Autowired SecurityQuestionRepository securityQuestionsRepository;
  @Autowired RoleRepository roleRepository;
  @Autowired MerchantRepository merchantRepository;
  
  private Users users;
  private Customer customer;
  private Merchant merchant;
  private List<SecurityAnswers> answers;
  private SecurityQuestions securityQuestions;
  private List<String> errors;

    public List<String> registerUser(CreateUserRequest createUserRequest) {
        errors = new ArrayList<String>();
        try {
            this.users = createUserRequest.getUsers();
            this.customer = createUserRequest.getCustomer();
            this.answers = createUserRequest.getAnswers();
            this.merchant=createUserRequest.getMerchant();
            
            validateObjectsPresent();
            validateSecurityAnswers();
            
            if (!errors.isEmpty()) {
                return errors;
            }
            
            if(this.users.getUserType()==UserType.CUSTOMER){
                validateCustomerInfo();
            }
            else if(this.users.getUserType()==UserType.MERCHANT){
                validateMerchantInfo();
            }
            else{
                errors.add("Unknown user type");
                return errors;
            }
            
            
            if (!errors.isEmpty()) {
                return errors;
            }

            validateUserRegistration();
            if (errors.isEmpty()) {
                save();
            }
        } catch (Exception e) {
            errors.add("Unknown error occured");
            logger.error("Error occurred while registering user", e);
        }
        return errors;
    }

    private void save() {
        if (this.users.getUserType() == UserType.CUSTOMER) {
            users.setCustomer(customer);
        } else if (this.users.getUserType() == UserType.MERCHANT) {
            users.setMerchant(merchant);
        }

        //Adding role 1 - USER to user
        Role role = roleRepository.findById(new Long(1));
        users.setRoles(new HashSet<Role>());
        users.getRoles().add(role);

        //Save user 
        Users newUser = userService.save(users);
        if (newUser == null) {
            errors.add("Cannot create users");
        } else {
            //Save Sequrity answers for user using newly created user id
            for (SecurityAnswers element : answers) {
                element.setUserId(newUser.getUserId());
                securityAnswersRepository.save(element);
            }
            //Save user to role using newly creted user id
            Set<Users> user_roles_ = role.getUsers();
            user_roles_.add(newUser);
            Role saved_roll = roleRepository.save(role);

            if (saved_roll == null) {
                errors.add("Issues with security role");
            }
            //Save customer
            if (this.users.getUserType() == UserType.CUSTOMER) {
                Customer saved_cust = customerRepository.save(customer);
                if (saved_cust == null) {
                    errors.add("Issues with saving personal details");
                }
            } else if (this.users.getUserType() == UserType.MERCHANT) {
                Merchant saved_merchant = merchantRepository.save(merchant);
                if (saved_merchant == null) {
                    errors.add("Issues with saving merchant details");
                }
            }
            
        }
    }

  private void validateUserRegistration() {
    
    if (userService.findByUsername(users.getUsername()) != null) {
      errors.add("User name already exists");
    }
    if(!lengthCheck(users.getUsername(),16,8)){
        errors.add("User name length violation");
    }
    if(!lengthCheck(users.getPassword(),16,8)){
        errors.add("Passwords length violation");
    }
    
    
    
  }
   private void validateCustomerInfo() {
       if(this.customer.getFirstName()==null ||this.customer.getFirstName().length()>45 ){
           errors.add("First name missing or too long");
       }
       if(this.customer.getLastName()==null ||this.customer.getLastName().length()>45 ){
           errors.add("Last name missing or too long");
       }
       if(!regexMatch(this.customer.getNic(),"[0-9]{9}v")){
           errors.add("National Identity Card number invalid");
       }
       if(!regexMatch(this.customer.getEmail(), "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")){
           errors.add("Email address invalid");
       }
       if(this.customer.getAddressLine1()==null ||this.customer.getAddressLine1().length()>45 ){
           errors.add("Address missing or too long");
       }
       
       
   } 
   private void validateMerchantInfo() {
       
       if(this.merchant.getOrgname()==null ||this.merchant.getOrgname().length()>45 ){
           errors.add("Organization name missing or too long");
       }
       if(this.merchant.getRegistrationno()==null ||this.merchant.getRegistrationno().length()>45 ){
           errors.add("Org registration no missing or too long");
       }
       if(this.merchant.getTaxno()==null ||this.merchant.getTaxno().length()>45 ){
           errors.add("Tax file no missing or too long");
       }

   }   
    private void validateSecurityAnswers() {
        int i = 1;
        for (SecurityAnswers element : answers) {
            if (element.getSecurityQuestions().getId() == null) {
                errors.add("Invalid security question " + i);
            } else {
                SecurityQuestions temp = securityQuestionsRepository.findById(element.getSecurityQuestions().getId());
                if (temp == null) {
                    errors.add("Invalid security question " + i);
                } else {
                    if (element.getAnswer() != null || element.getAnswer().length() > 45) {
                        element.setSecurityQuestions(temp);
                    } else {
                        errors.add("Invalid security answer " + i);
                    }
                }
            }
            i +=1;
        }
    }
  
  
  //check if all users, customer,sequrityAnswers objectrs 
    private void validateObjectsPresent() {
        if (this.users == null) {
            errors.add("User data incomplete");
        }
        if (this.customer == null) {
            errors.add("Personal data incomplete");
        }
        if (this.answers == null) {
            errors.add("security answer data incomplete");
        }

    }

    private boolean lengthCheck(String value, int maxLength, int minLength) {
        if (value == null) {
          return false;
        }
        if (value.length() <= maxLength && value.length() >= minLength) {
            return true;
        } else {
            return false;
        }
    }

    private boolean regexMatch(String value, String pattern) {
        Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = r.matcher(value);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }
}
