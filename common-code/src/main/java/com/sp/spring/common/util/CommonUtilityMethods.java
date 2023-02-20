package com.sp.spring.common.util;


import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AnonymousAuthenticationToken;

public class CommonUtilityMethods {

    /*public static String getUserIdFromToken(Authentication authentication) {
        String userId = null;
        if (context.getTokenType() == OAuth2TokenType.ACCESS_TOKEN) {
            Authentication principal = context.getPrincipal();
            userId = context.getClaims().get("Sub");
        }
        return userId;
    }*/

    public static String getUserIdFromToken(Authentication authentication) {
        String currentUserName = null;
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return currentUserName;
    }


    public static String getUserNameFromToken(Authentication authentication) {
       /* Map<String, String> map = (Map)((OAuth2Authentication) authentication).getUserAuthentication().getDetails();
        return map.get("user_name");*/
        return "user_Name";
    }
}
