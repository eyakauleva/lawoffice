package com.solvd.course.lawoffice.web.security;

import com.solvd.course.lawoffice.domain.security.UserDetailsImpl;
import com.solvd.course.lawoffice.web.dto.ReviewDto;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object returnObject;

    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean isMember(Long userId) {
        Long id = ((UserDetailsImpl) this.getPrincipal()).getId();
        return userId.equals(id);
    }

    public boolean isReviewOwner() {
        Long id = ((UserDetailsImpl) this.getPrincipal()).getId();
        return ((ReviewDto) this.returnObject).getClient().getUserId().equals(id);
    }

    @Override
    public void setFilterObject(Object filterObject) {

    }

    @Override
    public Object getFilterObject() {
        return null;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return null;
    }

}
