
package com.wac.domain;

// 사용자(회원)의 역할(권한) - 일반사용자, 관리자
// -> Spring Security에서 사용됨.
public enum UserRole {
    USER("ROLE_USER")
    , ADMIN("ROLE_ADMIN");
    
    
    private String role; 
    
    UserRole(String role){
        
        this.role = role;
        
    }
    
    public String getRole() {
        return this.role;
    } 
    
}  