package com.twisty.food.service;

import com.twisty.food.dto.RestaurantDto;
import com.twisty.food.entity.Address;
import com.twisty.food.entity.Restaurant;
import com.twisty.food.entity.USER_ROLE;
import com.twisty.food.entity.User;
import com.twisty.food.repository.AddressRepository;
import com.twisty.food.repository.RestaurantRepository;
import com.twisty.food.repository.UserRepository;
import com.twisty.food.request.ResturantRequestResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResturantServiceImpl implements ResturantService{
       @Autowired
       private  RestaurantRepository restaurantRepository;
       @Autowired
       private AddressRepository addressRepository;
       @Autowired
       private UserService userService;
       @Autowired
       private UserRepository userRepository;
    @Override
    public Restaurant CreateResturant(ResturantRequestResponce req, User user) throws Exception {

        if (user.getRole() == USER_ROLE.ROLE_ADMIN || user.getRole() == USER_ROLE.ROLE_RESTURANT_OWNER) {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(req.getId());
            restaurant.setDescription(req.getDescription());
            restaurant.setContactInformation(req.getContactInformation());
            restaurant.setCuisineType(req.getCuisineType());
            restaurant.setImages(req.getImages());
            restaurant.setOpeningHours(req.getOpeningHours());
            restaurant.setAddress(addressRepository.save(new Address()));
            restaurant.setOwner(user);
            return restaurantRepository.save(restaurant);
        }else {
            throw new Exception("Only Admin or resturant owner can create resturant");
        }
    }


    @Override
    public Restaurant UpdateResturantById(Long restaurantId, ResturantRequestResponce updateResturantRequest) throws Exception {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new Exception("Resturant Not Found"));
          if(restaurant.getDescription() !=null) {
              restaurant.setDescription(updateResturantRequest.getDescription());
          }
          if(restaurant.getContactInformation() !=null) {
              restaurant.setContactInformation(updateResturantRequest.getContactInformation());
          }
          if ((restaurant.getCuisineType() !=null) ) {
              restaurant.setCuisineType(updateResturantRequest.getCuisineType());
          }
          if(restaurant.getImages() !=null) {
              restaurant.setImages(updateResturantRequest.getImages());
          }
            if (restaurant.getOpeningHours() !=null) {
                restaurant.setOpeningHours(updateResturantRequest.getOpeningHours());
            }

            return restaurantRepository.save(restaurant);


    }

    @Override
    public void DeleteRestaurantById(Long restaurantId) throws Exception {

           restaurantRepository.deleteById(restaurantId);

    }

    @Override
    public Restaurant getRestaurantById(Long restaurantId) throws Exception {
        Restaurant byId = restaurantRepository.findById(restaurantId).orElseThrow(()-> new Exception("Could not find restaurant"));
        return byId;
    }

    @Override
    public List<Restaurant> getAllRestaurants() throws Exception {
         return  restaurantRepository.findAll();

    }

    @Override
    public List<Restaurant> SerchRestaurant(String query) throws Exception {
       return restaurantRepository.findBySearchResturants(query);

    }

    @Override
    public RestaurantDto addFavorites(Long restaurantId, User user) throws Exception {
        boolean isFavorites = false;
        Restaurant restaurant =getRestaurantById(restaurantId);
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setImages(restaurant.getImages());
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setTitle(restaurant.getRestaurantName());

        for(RestaurantDto favorite : user.getFavorites())
        {
            if(favorite.getId().equals(restaurantDto.getId()))
            {
                isFavorites = true;
                 break;
            }
        }
        if(isFavorites == false) {
            user.getFavorites().add(restaurantDto);
        }else {
            user.getFavorites().remove(restaurantDto);
        }
        userRepository.save(user);
        return restaurantDto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new Exception("Resturant Not Found"));
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
