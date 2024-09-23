package com.twisty.food.controller;

import com.twisty.food.config.JwtProvider;
import com.twisty.food.entity.Cart;
import com.twisty.food.entity.USER_ROLE;
import com.twisty.food.entity.User;
import com.twisty.food.repository.CartRepository;
import com.twisty.food.repository.UserRepository;
import com.twisty.food.request.LoginRequest;
import com.twisty.food.responce.AuthResponce;
import com.twisty.food.service.CustomeUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private PasswordEncoder passwordEncoder;
        @Autowired
        private JwtProvider jwtProvider;
        @Autowired
        private CustomeUserDetailsService customeUserDetailsService;
        @Autowired
        private CartRepository cartRepository;


        @PostMapping("/Signup")
        public ResponseEntity<AuthResponce> CreateUserHandler(@RequestBody User user) throws Exception {
                User byEmail = userRepository.findByEmail(user.getEmail());
                if (byEmail!=null) {
                        throw new Exception("email is used to another account ");
                }
                User createduser=new User();
                createduser.setEmail(user.getEmail());
                createduser.setFullname(user.getFullname());
                createduser.setRole(user.getRole());
                createduser.setPassword(passwordEncoder.encode(user.getPassword()));
                User save = userRepository.save(createduser);

                Cart cart = new Cart();
                cart.setCustomer(createduser);
                Cart save1 = cartRepository.save(cart);

               Authentication authentication  = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtProvider.genratetoken(authentication);

                AuthResponce authResponce = new AuthResponce();
                authResponce.setJwt(token);
                authResponce.setMessage("Register Success");
                authResponce.setUserRole(createduser.getRole());

                return ResponseEntity.ok(authResponce);
        }

        @PostMapping("/Signing")
        public ResponseEntity<AuthResponce> Signin(@RequestBody LoginRequest loginRequest){

                String username = loginRequest.getEmail();

                String password = loginRequest.getPassword();

                Authentication authentication = authentication(username,password);

                String token = jwtProvider.genratetoken(authentication);

                AuthResponce authResponce = new AuthResponce();
                authResponce.setJwt(token);
                authResponce.setMessage("Register Success");
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
                authResponce.setUserRole(USER_ROLE.valueOf(role));
                return new ResponseEntity<>(authResponce, HttpStatus.OK);




        }

          private Authentication authentication(String username, String password) {
                  UserDetails userDetails = customeUserDetailsService.loadUserByUsername(username);
                  if(userDetails==null){
                          throw new BadCredentialsException("Invalid Username");
                  }
                  if(!passwordEncoder.matches(password, userDetails.getPassword())){
                          throw new BadCredentialsException("Invalid Password");
                  }
                  return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

          }


}
