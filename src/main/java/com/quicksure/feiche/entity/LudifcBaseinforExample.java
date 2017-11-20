package com.quicksure.feiche.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LudifcBaseinforExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LudifcBaseinforExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andOrderidIsNull() {
            addCriterion("orderId is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderId is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(Integer value) {
            addCriterion("orderId =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(Integer value) {
            addCriterion("orderId <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(Integer value) {
            addCriterion("orderId >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderId >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(Integer value) {
            addCriterion("orderId <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(Integer value) {
            addCriterion("orderId <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<Integer> values) {
            addCriterion("orderId in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<Integer> values) {
            addCriterion("orderId not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(Integer value1, Integer value2) {
            addCriterion("orderId between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(Integer value1, Integer value2) {
            addCriterion("orderId not between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrdernoIsNull() {
            addCriterion("orderNo is null");
            return (Criteria) this;
        }

        public Criteria andOrdernoIsNotNull() {
            addCriterion("orderNo is not null");
            return (Criteria) this;
        }

        public Criteria andOrdernoEqualTo(String value) {
            addCriterion("orderNo =", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotEqualTo(String value) {
            addCriterion("orderNo <>", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoGreaterThan(String value) {
            addCriterion("orderNo >", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoGreaterThanOrEqualTo(String value) {
            addCriterion("orderNo >=", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLessThan(String value) {
            addCriterion("orderNo <", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLessThanOrEqualTo(String value) {
            addCriterion("orderNo <=", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLike(String value) {
            addCriterion("orderNo like", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotLike(String value) {
            addCriterion("orderNo not like", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoIn(List<String> values) {
            addCriterion("orderNo in", values, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotIn(List<String> values) {
            addCriterion("orderNo not in", values, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoBetween(String value1, String value2) {
            addCriterion("orderNo between", value1, value2, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotBetween(String value1, String value2) {
            addCriterion("orderNo not between", value1, value2, "orderno");
            return (Criteria) this;
        }

        public Criteria andUserinforidIsNull() {
            addCriterion("userinforId is null");
            return (Criteria) this;
        }

        public Criteria andUserinforidIsNotNull() {
            addCriterion("userinforId is not null");
            return (Criteria) this;
        }

        public Criteria andUserinforidEqualTo(Integer value) {
            addCriterion("userinforId =", value, "userinforid");
            return (Criteria) this;
        }

        public Criteria andUserinforidNotEqualTo(Integer value) {
            addCriterion("userinforId <>", value, "userinforid");
            return (Criteria) this;
        }

        public Criteria andUserinforidGreaterThan(Integer value) {
            addCriterion("userinforId >", value, "userinforid");
            return (Criteria) this;
        }

        public Criteria andUserinforidGreaterThanOrEqualTo(Integer value) {
            addCriterion("userinforId >=", value, "userinforid");
            return (Criteria) this;
        }

        public Criteria andUserinforidLessThan(Integer value) {
            addCriterion("userinforId <", value, "userinforid");
            return (Criteria) this;
        }

        public Criteria andUserinforidLessThanOrEqualTo(Integer value) {
            addCriterion("userinforId <=", value, "userinforid");
            return (Criteria) this;
        }

        public Criteria andUserinforidIn(List<Integer> values) {
            addCriterion("userinforId in", values, "userinforid");
            return (Criteria) this;
        }

        public Criteria andUserinforidNotIn(List<Integer> values) {
            addCriterion("userinforId not in", values, "userinforid");
            return (Criteria) this;
        }

        public Criteria andUserinforidBetween(Integer value1, Integer value2) {
            addCriterion("userinforId between", value1, value2, "userinforid");
            return (Criteria) this;
        }

        public Criteria andUserinforidNotBetween(Integer value1, Integer value2) {
            addCriterion("userinforId not between", value1, value2, "userinforid");
            return (Criteria) this;
        }

        public Criteria andOrderstateIsNull() {
            addCriterion("orderstate is null");
            return (Criteria) this;
        }

        public Criteria andOrderstateIsNotNull() {
            addCriterion("orderstate is not null");
            return (Criteria) this;
        }

        public Criteria andOrderstateEqualTo(Integer value) {
            addCriterion("orderstate =", value, "orderstate");
            return (Criteria) this;
        }

        public Criteria andOrderstateNotEqualTo(Integer value) {
            addCriterion("orderstate <>", value, "orderstate");
            return (Criteria) this;
        }

        public Criteria andOrderstateGreaterThan(Integer value) {
            addCriterion("orderstate >", value, "orderstate");
            return (Criteria) this;
        }

        public Criteria andOrderstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderstate >=", value, "orderstate");
            return (Criteria) this;
        }

        public Criteria andOrderstateLessThan(Integer value) {
            addCriterion("orderstate <", value, "orderstate");
            return (Criteria) this;
        }

        public Criteria andOrderstateLessThanOrEqualTo(Integer value) {
            addCriterion("orderstate <=", value, "orderstate");
            return (Criteria) this;
        }

        public Criteria andOrderstateIn(List<Integer> values) {
            addCriterion("orderstate in", values, "orderstate");
            return (Criteria) this;
        }

        public Criteria andOrderstateNotIn(List<Integer> values) {
            addCriterion("orderstate not in", values, "orderstate");
            return (Criteria) this;
        }

        public Criteria andOrderstateBetween(Integer value1, Integer value2) {
            addCriterion("orderstate between", value1, value2, "orderstate");
            return (Criteria) this;
        }

        public Criteria andOrderstateNotBetween(Integer value1, Integer value2) {
            addCriterion("orderstate not between", value1, value2, "orderstate");
            return (Criteria) this;
        }

        public Criteria andApplicationnameIsNull() {
            addCriterion("applicationName is null");
            return (Criteria) this;
        }

        public Criteria andApplicationnameIsNotNull() {
            addCriterion("applicationName is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationnameEqualTo(String value) {
            addCriterion("applicationName =", value, "applicationname");
            return (Criteria) this;
        }

        public Criteria andApplicationnameNotEqualTo(String value) {
            addCriterion("applicationName <>", value, "applicationname");
            return (Criteria) this;
        }

        public Criteria andApplicationnameGreaterThan(String value) {
            addCriterion("applicationName >", value, "applicationname");
            return (Criteria) this;
        }

        public Criteria andApplicationnameGreaterThanOrEqualTo(String value) {
            addCriterion("applicationName >=", value, "applicationname");
            return (Criteria) this;
        }

        public Criteria andApplicationnameLessThan(String value) {
            addCriterion("applicationName <", value, "applicationname");
            return (Criteria) this;
        }

        public Criteria andApplicationnameLessThanOrEqualTo(String value) {
            addCriterion("applicationName <=", value, "applicationname");
            return (Criteria) this;
        }

        public Criteria andApplicationnameLike(String value) {
            addCriterion("applicationName like", value, "applicationname");
            return (Criteria) this;
        }

        public Criteria andApplicationnameNotLike(String value) {
            addCriterion("applicationName not like", value, "applicationname");
            return (Criteria) this;
        }

        public Criteria andApplicationnameIn(List<String> values) {
            addCriterion("applicationName in", values, "applicationname");
            return (Criteria) this;
        }

        public Criteria andApplicationnameNotIn(List<String> values) {
            addCriterion("applicationName not in", values, "applicationname");
            return (Criteria) this;
        }

        public Criteria andApplicationnameBetween(String value1, String value2) {
            addCriterion("applicationName between", value1, value2, "applicationname");
            return (Criteria) this;
        }

        public Criteria andApplicationnameNotBetween(String value1, String value2) {
            addCriterion("applicationName not between", value1, value2, "applicationname");
            return (Criteria) this;
        }

        public Criteria andInsurernameIsNull() {
            addCriterion("insurerName is null");
            return (Criteria) this;
        }

        public Criteria andInsurernameIsNotNull() {
            addCriterion("insurerName is not null");
            return (Criteria) this;
        }

        public Criteria andInsurernameEqualTo(String value) {
            addCriterion("insurerName =", value, "insurername");
            return (Criteria) this;
        }

        public Criteria andInsurernameNotEqualTo(String value) {
            addCriterion("insurerName <>", value, "insurername");
            return (Criteria) this;
        }

        public Criteria andInsurernameGreaterThan(String value) {
            addCriterion("insurerName >", value, "insurername");
            return (Criteria) this;
        }

        public Criteria andInsurernameGreaterThanOrEqualTo(String value) {
            addCriterion("insurerName >=", value, "insurername");
            return (Criteria) this;
        }

        public Criteria andInsurernameLessThan(String value) {
            addCriterion("insurerName <", value, "insurername");
            return (Criteria) this;
        }

        public Criteria andInsurernameLessThanOrEqualTo(String value) {
            addCriterion("insurerName <=", value, "insurername");
            return (Criteria) this;
        }

        public Criteria andInsurernameLike(String value) {
            addCriterion("insurerName like", value, "insurername");
            return (Criteria) this;
        }

        public Criteria andInsurernameNotLike(String value) {
            addCriterion("insurerName not like", value, "insurername");
            return (Criteria) this;
        }

        public Criteria andInsurernameIn(List<String> values) {
            addCriterion("insurerName in", values, "insurername");
            return (Criteria) this;
        }

        public Criteria andInsurernameNotIn(List<String> values) {
            addCriterion("insurerName not in", values, "insurername");
            return (Criteria) this;
        }

        public Criteria andInsurernameBetween(String value1, String value2) {
            addCriterion("insurerName between", value1, value2, "insurername");
            return (Criteria) this;
        }

        public Criteria andInsurernameNotBetween(String value1, String value2) {
            addCriterion("insurerName not between", value1, value2, "insurername");
            return (Criteria) this;
        }

        public Criteria andAppSexIsNull() {
            addCriterion("app_sex is null");
            return (Criteria) this;
        }

        public Criteria andAppSexIsNotNull() {
            addCriterion("app_sex is not null");
            return (Criteria) this;
        }

        public Criteria andAppSexEqualTo(String value) {
            addCriterion("app_sex =", value, "appSex");
            return (Criteria) this;
        }

        public Criteria andAppSexNotEqualTo(String value) {
            addCriterion("app_sex <>", value, "appSex");
            return (Criteria) this;
        }

        public Criteria andAppSexGreaterThan(String value) {
            addCriterion("app_sex >", value, "appSex");
            return (Criteria) this;
        }

        public Criteria andAppSexGreaterThanOrEqualTo(String value) {
            addCriterion("app_sex >=", value, "appSex");
            return (Criteria) this;
        }

        public Criteria andAppSexLessThan(String value) {
            addCriterion("app_sex <", value, "appSex");
            return (Criteria) this;
        }

        public Criteria andAppSexLessThanOrEqualTo(String value) {
            addCriterion("app_sex <=", value, "appSex");
            return (Criteria) this;
        }

        public Criteria andAppSexLike(String value) {
            addCriterion("app_sex like", value, "appSex");
            return (Criteria) this;
        }

        public Criteria andAppSexNotLike(String value) {
            addCriterion("app_sex not like", value, "appSex");
            return (Criteria) this;
        }

        public Criteria andAppSexIn(List<String> values) {
            addCriterion("app_sex in", values, "appSex");
            return (Criteria) this;
        }

        public Criteria andAppSexNotIn(List<String> values) {
            addCriterion("app_sex not in", values, "appSex");
            return (Criteria) this;
        }

        public Criteria andAppSexBetween(String value1, String value2) {
            addCriterion("app_sex between", value1, value2, "appSex");
            return (Criteria) this;
        }

        public Criteria andAppSexNotBetween(String value1, String value2) {
            addCriterion("app_sex not between", value1, value2, "appSex");
            return (Criteria) this;
        }

        public Criteria andInsSexIsNull() {
            addCriterion("ins_sex is null");
            return (Criteria) this;
        }

        public Criteria andInsSexIsNotNull() {
            addCriterion("ins_sex is not null");
            return (Criteria) this;
        }

        public Criteria andInsSexEqualTo(String value) {
            addCriterion("ins_sex =", value, "insSex");
            return (Criteria) this;
        }

        public Criteria andInsSexNotEqualTo(String value) {
            addCriterion("ins_sex <>", value, "insSex");
            return (Criteria) this;
        }

        public Criteria andInsSexGreaterThan(String value) {
            addCriterion("ins_sex >", value, "insSex");
            return (Criteria) this;
        }

        public Criteria andInsSexGreaterThanOrEqualTo(String value) {
            addCriterion("ins_sex >=", value, "insSex");
            return (Criteria) this;
        }

        public Criteria andInsSexLessThan(String value) {
            addCriterion("ins_sex <", value, "insSex");
            return (Criteria) this;
        }

        public Criteria andInsSexLessThanOrEqualTo(String value) {
            addCriterion("ins_sex <=", value, "insSex");
            return (Criteria) this;
        }

        public Criteria andInsSexLike(String value) {
            addCriterion("ins_sex like", value, "insSex");
            return (Criteria) this;
        }

        public Criteria andInsSexNotLike(String value) {
            addCriterion("ins_sex not like", value, "insSex");
            return (Criteria) this;
        }

        public Criteria andInsSexIn(List<String> values) {
            addCriterion("ins_sex in", values, "insSex");
            return (Criteria) this;
        }

        public Criteria andInsSexNotIn(List<String> values) {
            addCriterion("ins_sex not in", values, "insSex");
            return (Criteria) this;
        }

        public Criteria andInsSexBetween(String value1, String value2) {
            addCriterion("ins_sex between", value1, value2, "insSex");
            return (Criteria) this;
        }

        public Criteria andInsSexNotBetween(String value1, String value2) {
            addCriterion("ins_sex not between", value1, value2, "insSex");
            return (Criteria) this;
        }

        public Criteria andAppEmailIsNull() {
            addCriterion("app_email is null");
            return (Criteria) this;
        }

        public Criteria andAppEmailIsNotNull() {
            addCriterion("app_email is not null");
            return (Criteria) this;
        }

        public Criteria andAppEmailEqualTo(String value) {
            addCriterion("app_email =", value, "appEmail");
            return (Criteria) this;
        }

        public Criteria andAppEmailNotEqualTo(String value) {
            addCriterion("app_email <>", value, "appEmail");
            return (Criteria) this;
        }

        public Criteria andAppEmailGreaterThan(String value) {
            addCriterion("app_email >", value, "appEmail");
            return (Criteria) this;
        }

        public Criteria andAppEmailGreaterThanOrEqualTo(String value) {
            addCriterion("app_email >=", value, "appEmail");
            return (Criteria) this;
        }

        public Criteria andAppEmailLessThan(String value) {
            addCriterion("app_email <", value, "appEmail");
            return (Criteria) this;
        }

        public Criteria andAppEmailLessThanOrEqualTo(String value) {
            addCriterion("app_email <=", value, "appEmail");
            return (Criteria) this;
        }

        public Criteria andAppEmailLike(String value) {
            addCriterion("app_email like", value, "appEmail");
            return (Criteria) this;
        }

        public Criteria andAppEmailNotLike(String value) {
            addCriterion("app_email not like", value, "appEmail");
            return (Criteria) this;
        }

        public Criteria andAppEmailIn(List<String> values) {
            addCriterion("app_email in", values, "appEmail");
            return (Criteria) this;
        }

        public Criteria andAppEmailNotIn(List<String> values) {
            addCriterion("app_email not in", values, "appEmail");
            return (Criteria) this;
        }

        public Criteria andAppEmailBetween(String value1, String value2) {
            addCriterion("app_email between", value1, value2, "appEmail");
            return (Criteria) this;
        }

        public Criteria andAppEmailNotBetween(String value1, String value2) {
            addCriterion("app_email not between", value1, value2, "appEmail");
            return (Criteria) this;
        }

        public Criteria andInsEmailIsNull() {
            addCriterion("ins_email is null");
            return (Criteria) this;
        }

        public Criteria andInsEmailIsNotNull() {
            addCriterion("ins_email is not null");
            return (Criteria) this;
        }

        public Criteria andInsEmailEqualTo(String value) {
            addCriterion("ins_email =", value, "insEmail");
            return (Criteria) this;
        }

        public Criteria andInsEmailNotEqualTo(String value) {
            addCriterion("ins_email <>", value, "insEmail");
            return (Criteria) this;
        }

        public Criteria andInsEmailGreaterThan(String value) {
            addCriterion("ins_email >", value, "insEmail");
            return (Criteria) this;
        }

        public Criteria andInsEmailGreaterThanOrEqualTo(String value) {
            addCriterion("ins_email >=", value, "insEmail");
            return (Criteria) this;
        }

        public Criteria andInsEmailLessThan(String value) {
            addCriterion("ins_email <", value, "insEmail");
            return (Criteria) this;
        }

        public Criteria andInsEmailLessThanOrEqualTo(String value) {
            addCriterion("ins_email <=", value, "insEmail");
            return (Criteria) this;
        }

        public Criteria andInsEmailLike(String value) {
            addCriterion("ins_email like", value, "insEmail");
            return (Criteria) this;
        }

        public Criteria andInsEmailNotLike(String value) {
            addCriterion("ins_email not like", value, "insEmail");
            return (Criteria) this;
        }

        public Criteria andInsEmailIn(List<String> values) {
            addCriterion("ins_email in", values, "insEmail");
            return (Criteria) this;
        }

        public Criteria andInsEmailNotIn(List<String> values) {
            addCriterion("ins_email not in", values, "insEmail");
            return (Criteria) this;
        }

        public Criteria andInsEmailBetween(String value1, String value2) {
            addCriterion("ins_email between", value1, value2, "insEmail");
            return (Criteria) this;
        }

        public Criteria andInsEmailNotBetween(String value1, String value2) {
            addCriterion("ins_email not between", value1, value2, "insEmail");
            return (Criteria) this;
        }

        public Criteria andAppCertTypeIsNull() {
            addCriterion("app_cert_type is null");
            return (Criteria) this;
        }

        public Criteria andAppCertTypeIsNotNull() {
            addCriterion("app_cert_type is not null");
            return (Criteria) this;
        }

        public Criteria andAppCertTypeEqualTo(String value) {
            addCriterion("app_cert_type =", value, "appCertType");
            return (Criteria) this;
        }

        public Criteria andAppCertTypeNotEqualTo(String value) {
            addCriterion("app_cert_type <>", value, "appCertType");
            return (Criteria) this;
        }

        public Criteria andAppCertTypeGreaterThan(String value) {
            addCriterion("app_cert_type >", value, "appCertType");
            return (Criteria) this;
        }

        public Criteria andAppCertTypeGreaterThanOrEqualTo(String value) {
            addCriterion("app_cert_type >=", value, "appCertType");
            return (Criteria) this;
        }

        public Criteria andAppCertTypeLessThan(String value) {
            addCriterion("app_cert_type <", value, "appCertType");
            return (Criteria) this;
        }

        public Criteria andAppCertTypeLessThanOrEqualTo(String value) {
            addCriterion("app_cert_type <=", value, "appCertType");
            return (Criteria) this;
        }

        public Criteria andAppCertTypeLike(String value) {
            addCriterion("app_cert_type like", value, "appCertType");
            return (Criteria) this;
        }

        public Criteria andAppCertTypeNotLike(String value) {
            addCriterion("app_cert_type not like", value, "appCertType");
            return (Criteria) this;
        }

        public Criteria andAppCertTypeIn(List<String> values) {
            addCriterion("app_cert_type in", values, "appCertType");
            return (Criteria) this;
        }

        public Criteria andAppCertTypeNotIn(List<String> values) {
            addCriterion("app_cert_type not in", values, "appCertType");
            return (Criteria) this;
        }

        public Criteria andAppCertTypeBetween(String value1, String value2) {
            addCriterion("app_cert_type between", value1, value2, "appCertType");
            return (Criteria) this;
        }

        public Criteria andAppCertTypeNotBetween(String value1, String value2) {
            addCriterion("app_cert_type not between", value1, value2, "appCertType");
            return (Criteria) this;
        }

        public Criteria andInsCertTypeIsNull() {
            addCriterion("ins_cert_type is null");
            return (Criteria) this;
        }

        public Criteria andInsCertTypeIsNotNull() {
            addCriterion("ins_cert_type is not null");
            return (Criteria) this;
        }

        public Criteria andInsCertTypeEqualTo(String value) {
            addCriterion("ins_cert_type =", value, "insCertType");
            return (Criteria) this;
        }

        public Criteria andInsCertTypeNotEqualTo(String value) {
            addCriterion("ins_cert_type <>", value, "insCertType");
            return (Criteria) this;
        }

        public Criteria andInsCertTypeGreaterThan(String value) {
            addCriterion("ins_cert_type >", value, "insCertType");
            return (Criteria) this;
        }

        public Criteria andInsCertTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ins_cert_type >=", value, "insCertType");
            return (Criteria) this;
        }

        public Criteria andInsCertTypeLessThan(String value) {
            addCriterion("ins_cert_type <", value, "insCertType");
            return (Criteria) this;
        }

        public Criteria andInsCertTypeLessThanOrEqualTo(String value) {
            addCriterion("ins_cert_type <=", value, "insCertType");
            return (Criteria) this;
        }

        public Criteria andInsCertTypeLike(String value) {
            addCriterion("ins_cert_type like", value, "insCertType");
            return (Criteria) this;
        }

        public Criteria andInsCertTypeNotLike(String value) {
            addCriterion("ins_cert_type not like", value, "insCertType");
            return (Criteria) this;
        }

        public Criteria andInsCertTypeIn(List<String> values) {
            addCriterion("ins_cert_type in", values, "insCertType");
            return (Criteria) this;
        }

        public Criteria andInsCertTypeNotIn(List<String> values) {
            addCriterion("ins_cert_type not in", values, "insCertType");
            return (Criteria) this;
        }

        public Criteria andInsCertTypeBetween(String value1, String value2) {
            addCriterion("ins_cert_type between", value1, value2, "insCertType");
            return (Criteria) this;
        }

        public Criteria andInsCertTypeNotBetween(String value1, String value2) {
            addCriterion("ins_cert_type not between", value1, value2, "insCertType");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNull() {
            addCriterion("app_ID is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("app_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(String value) {
            addCriterion("app_ID =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(String value) {
            addCriterion("app_ID <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(String value) {
            addCriterion("app_ID >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("app_ID >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(String value) {
            addCriterion("app_ID <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(String value) {
            addCriterion("app_ID <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLike(String value) {
            addCriterion("app_ID like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotLike(String value) {
            addCriterion("app_ID not like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<String> values) {
            addCriterion("app_ID in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<String> values) {
            addCriterion("app_ID not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(String value1, String value2) {
            addCriterion("app_ID between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(String value1, String value2) {
            addCriterion("app_ID not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andInsIdIsNull() {
            addCriterion("ins_ID is null");
            return (Criteria) this;
        }

        public Criteria andInsIdIsNotNull() {
            addCriterion("ins_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInsIdEqualTo(String value) {
            addCriterion("ins_ID =", value, "insId");
            return (Criteria) this;
        }

        public Criteria andInsIdNotEqualTo(String value) {
            addCriterion("ins_ID <>", value, "insId");
            return (Criteria) this;
        }

        public Criteria andInsIdGreaterThan(String value) {
            addCriterion("ins_ID >", value, "insId");
            return (Criteria) this;
        }

        public Criteria andInsIdGreaterThanOrEqualTo(String value) {
            addCriterion("ins_ID >=", value, "insId");
            return (Criteria) this;
        }

        public Criteria andInsIdLessThan(String value) {
            addCriterion("ins_ID <", value, "insId");
            return (Criteria) this;
        }

        public Criteria andInsIdLessThanOrEqualTo(String value) {
            addCriterion("ins_ID <=", value, "insId");
            return (Criteria) this;
        }

        public Criteria andInsIdLike(String value) {
            addCriterion("ins_ID like", value, "insId");
            return (Criteria) this;
        }

        public Criteria andInsIdNotLike(String value) {
            addCriterion("ins_ID not like", value, "insId");
            return (Criteria) this;
        }

        public Criteria andInsIdIn(List<String> values) {
            addCriterion("ins_ID in", values, "insId");
            return (Criteria) this;
        }

        public Criteria andInsIdNotIn(List<String> values) {
            addCriterion("ins_ID not in", values, "insId");
            return (Criteria) this;
        }

        public Criteria andInsIdBetween(String value1, String value2) {
            addCriterion("ins_ID between", value1, value2, "insId");
            return (Criteria) this;
        }

        public Criteria andInsIdNotBetween(String value1, String value2) {
            addCriterion("ins_ID not between", value1, value2, "insId");
            return (Criteria) this;
        }

        public Criteria andAppBirthdayIsNull() {
            addCriterion("app_birthday is null");
            return (Criteria) this;
        }

        public Criteria andAppBirthdayIsNotNull() {
            addCriterion("app_birthday is not null");
            return (Criteria) this;
        }

        public Criteria andAppBirthdayEqualTo(String value) {
            addCriterion("app_birthday =", value, "appBirthday");
            return (Criteria) this;
        }

        public Criteria andAppBirthdayNotEqualTo(String value) {
            addCriterion("app_birthday <>", value, "appBirthday");
            return (Criteria) this;
        }

        public Criteria andAppBirthdayGreaterThan(String value) {
            addCriterion("app_birthday >", value, "appBirthday");
            return (Criteria) this;
        }

        public Criteria andAppBirthdayGreaterThanOrEqualTo(String value) {
            addCriterion("app_birthday >=", value, "appBirthday");
            return (Criteria) this;
        }

        public Criteria andAppBirthdayLessThan(String value) {
            addCriterion("app_birthday <", value, "appBirthday");
            return (Criteria) this;
        }

        public Criteria andAppBirthdayLessThanOrEqualTo(String value) {
            addCriterion("app_birthday <=", value, "appBirthday");
            return (Criteria) this;
        }

        public Criteria andAppBirthdayLike(String value) {
            addCriterion("app_birthday like", value, "appBirthday");
            return (Criteria) this;
        }

        public Criteria andAppBirthdayNotLike(String value) {
            addCriterion("app_birthday not like", value, "appBirthday");
            return (Criteria) this;
        }

        public Criteria andAppBirthdayIn(List<String> values) {
            addCriterion("app_birthday in", values, "appBirthday");
            return (Criteria) this;
        }

        public Criteria andAppBirthdayNotIn(List<String> values) {
            addCriterion("app_birthday not in", values, "appBirthday");
            return (Criteria) this;
        }

        public Criteria andAppBirthdayBetween(String value1, String value2) {
            addCriterion("app_birthday between", value1, value2, "appBirthday");
            return (Criteria) this;
        }

        public Criteria andAppBirthdayNotBetween(String value1, String value2) {
            addCriterion("app_birthday not between", value1, value2, "appBirthday");
            return (Criteria) this;
        }

        public Criteria andInsBirthdayIsNull() {
            addCriterion("ins_birthday is null");
            return (Criteria) this;
        }

        public Criteria andInsBirthdayIsNotNull() {
            addCriterion("ins_birthday is not null");
            return (Criteria) this;
        }

        public Criteria andInsBirthdayEqualTo(String value) {
            addCriterion("ins_birthday =", value, "insBirthday");
            return (Criteria) this;
        }

        public Criteria andInsBirthdayNotEqualTo(String value) {
            addCriterion("ins_birthday <>", value, "insBirthday");
            return (Criteria) this;
        }

        public Criteria andInsBirthdayGreaterThan(String value) {
            addCriterion("ins_birthday >", value, "insBirthday");
            return (Criteria) this;
        }

        public Criteria andInsBirthdayGreaterThanOrEqualTo(String value) {
            addCriterion("ins_birthday >=", value, "insBirthday");
            return (Criteria) this;
        }

        public Criteria andInsBirthdayLessThan(String value) {
            addCriterion("ins_birthday <", value, "insBirthday");
            return (Criteria) this;
        }

        public Criteria andInsBirthdayLessThanOrEqualTo(String value) {
            addCriterion("ins_birthday <=", value, "insBirthday");
            return (Criteria) this;
        }

        public Criteria andInsBirthdayLike(String value) {
            addCriterion("ins_birthday like", value, "insBirthday");
            return (Criteria) this;
        }

        public Criteria andInsBirthdayNotLike(String value) {
            addCriterion("ins_birthday not like", value, "insBirthday");
            return (Criteria) this;
        }

        public Criteria andInsBirthdayIn(List<String> values) {
            addCriterion("ins_birthday in", values, "insBirthday");
            return (Criteria) this;
        }

        public Criteria andInsBirthdayNotIn(List<String> values) {
            addCriterion("ins_birthday not in", values, "insBirthday");
            return (Criteria) this;
        }

        public Criteria andInsBirthdayBetween(String value1, String value2) {
            addCriterion("ins_birthday between", value1, value2, "insBirthday");
            return (Criteria) this;
        }

        public Criteria andInsBirthdayNotBetween(String value1, String value2) {
            addCriterion("ins_birthday not between", value1, value2, "insBirthday");
            return (Criteria) this;
        }

        public Criteria andAppAddrIsNull() {
            addCriterion("app_addr is null");
            return (Criteria) this;
        }

        public Criteria andAppAddrIsNotNull() {
            addCriterion("app_addr is not null");
            return (Criteria) this;
        }

        public Criteria andAppAddrEqualTo(String value) {
            addCriterion("app_addr =", value, "appAddr");
            return (Criteria) this;
        }

        public Criteria andAppAddrNotEqualTo(String value) {
            addCriterion("app_addr <>", value, "appAddr");
            return (Criteria) this;
        }

        public Criteria andAppAddrGreaterThan(String value) {
            addCriterion("app_addr >", value, "appAddr");
            return (Criteria) this;
        }

        public Criteria andAppAddrGreaterThanOrEqualTo(String value) {
            addCriterion("app_addr >=", value, "appAddr");
            return (Criteria) this;
        }

        public Criteria andAppAddrLessThan(String value) {
            addCriterion("app_addr <", value, "appAddr");
            return (Criteria) this;
        }

        public Criteria andAppAddrLessThanOrEqualTo(String value) {
            addCriterion("app_addr <=", value, "appAddr");
            return (Criteria) this;
        }

        public Criteria andAppAddrLike(String value) {
            addCriterion("app_addr like", value, "appAddr");
            return (Criteria) this;
        }

        public Criteria andAppAddrNotLike(String value) {
            addCriterion("app_addr not like", value, "appAddr");
            return (Criteria) this;
        }

        public Criteria andAppAddrIn(List<String> values) {
            addCriterion("app_addr in", values, "appAddr");
            return (Criteria) this;
        }

        public Criteria andAppAddrNotIn(List<String> values) {
            addCriterion("app_addr not in", values, "appAddr");
            return (Criteria) this;
        }

        public Criteria andAppAddrBetween(String value1, String value2) {
            addCriterion("app_addr between", value1, value2, "appAddr");
            return (Criteria) this;
        }

        public Criteria andAppAddrNotBetween(String value1, String value2) {
            addCriterion("app_addr not between", value1, value2, "appAddr");
            return (Criteria) this;
        }

        public Criteria andInsAddrIsNull() {
            addCriterion("ins_addr is null");
            return (Criteria) this;
        }

        public Criteria andInsAddrIsNotNull() {
            addCriterion("ins_addr is not null");
            return (Criteria) this;
        }

        public Criteria andInsAddrEqualTo(String value) {
            addCriterion("ins_addr =", value, "insAddr");
            return (Criteria) this;
        }

        public Criteria andInsAddrNotEqualTo(String value) {
            addCriterion("ins_addr <>", value, "insAddr");
            return (Criteria) this;
        }

        public Criteria andInsAddrGreaterThan(String value) {
            addCriterion("ins_addr >", value, "insAddr");
            return (Criteria) this;
        }

        public Criteria andInsAddrGreaterThanOrEqualTo(String value) {
            addCriterion("ins_addr >=", value, "insAddr");
            return (Criteria) this;
        }

        public Criteria andInsAddrLessThan(String value) {
            addCriterion("ins_addr <", value, "insAddr");
            return (Criteria) this;
        }

        public Criteria andInsAddrLessThanOrEqualTo(String value) {
            addCriterion("ins_addr <=", value, "insAddr");
            return (Criteria) this;
        }

        public Criteria andInsAddrLike(String value) {
            addCriterion("ins_addr like", value, "insAddr");
            return (Criteria) this;
        }

        public Criteria andInsAddrNotLike(String value) {
            addCriterion("ins_addr not like", value, "insAddr");
            return (Criteria) this;
        }

        public Criteria andInsAddrIn(List<String> values) {
            addCriterion("ins_addr in", values, "insAddr");
            return (Criteria) this;
        }

        public Criteria andInsAddrNotIn(List<String> values) {
            addCriterion("ins_addr not in", values, "insAddr");
            return (Criteria) this;
        }

        public Criteria andInsAddrBetween(String value1, String value2) {
            addCriterion("ins_addr between", value1, value2, "insAddr");
            return (Criteria) this;
        }

        public Criteria andInsAddrNotBetween(String value1, String value2) {
            addCriterion("ins_addr not between", value1, value2, "insAddr");
            return (Criteria) this;
        }

        public Criteria andAppPhoneIsNull() {
            addCriterion("app_phone is null");
            return (Criteria) this;
        }

        public Criteria andAppPhoneIsNotNull() {
            addCriterion("app_phone is not null");
            return (Criteria) this;
        }

        public Criteria andAppPhoneEqualTo(String value) {
            addCriterion("app_phone =", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneNotEqualTo(String value) {
            addCriterion("app_phone <>", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneGreaterThan(String value) {
            addCriterion("app_phone >", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("app_phone >=", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneLessThan(String value) {
            addCriterion("app_phone <", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneLessThanOrEqualTo(String value) {
            addCriterion("app_phone <=", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneLike(String value) {
            addCriterion("app_phone like", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneNotLike(String value) {
            addCriterion("app_phone not like", value, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneIn(List<String> values) {
            addCriterion("app_phone in", values, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneNotIn(List<String> values) {
            addCriterion("app_phone not in", values, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneBetween(String value1, String value2) {
            addCriterion("app_phone between", value1, value2, "appPhone");
            return (Criteria) this;
        }

        public Criteria andAppPhoneNotBetween(String value1, String value2) {
            addCriterion("app_phone not between", value1, value2, "appPhone");
            return (Criteria) this;
        }

        public Criteria andInsPhoneIsNull() {
            addCriterion("ins_phone is null");
            return (Criteria) this;
        }

        public Criteria andInsPhoneIsNotNull() {
            addCriterion("ins_phone is not null");
            return (Criteria) this;
        }

        public Criteria andInsPhoneEqualTo(String value) {
            addCriterion("ins_phone =", value, "insPhone");
            return (Criteria) this;
        }

        public Criteria andInsPhoneNotEqualTo(String value) {
            addCriterion("ins_phone <>", value, "insPhone");
            return (Criteria) this;
        }

        public Criteria andInsPhoneGreaterThan(String value) {
            addCriterion("ins_phone >", value, "insPhone");
            return (Criteria) this;
        }

        public Criteria andInsPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("ins_phone >=", value, "insPhone");
            return (Criteria) this;
        }

        public Criteria andInsPhoneLessThan(String value) {
            addCriterion("ins_phone <", value, "insPhone");
            return (Criteria) this;
        }

        public Criteria andInsPhoneLessThanOrEqualTo(String value) {
            addCriterion("ins_phone <=", value, "insPhone");
            return (Criteria) this;
        }

        public Criteria andInsPhoneLike(String value) {
            addCriterion("ins_phone like", value, "insPhone");
            return (Criteria) this;
        }

        public Criteria andInsPhoneNotLike(String value) {
            addCriterion("ins_phone not like", value, "insPhone");
            return (Criteria) this;
        }

        public Criteria andInsPhoneIn(List<String> values) {
            addCriterion("ins_phone in", values, "insPhone");
            return (Criteria) this;
        }

        public Criteria andInsPhoneNotIn(List<String> values) {
            addCriterion("ins_phone not in", values, "insPhone");
            return (Criteria) this;
        }

        public Criteria andInsPhoneBetween(String value1, String value2) {
            addCriterion("ins_phone between", value1, value2, "insPhone");
            return (Criteria) this;
        }

        public Criteria andInsPhoneNotBetween(String value1, String value2) {
            addCriterion("ins_phone not between", value1, value2, "insPhone");
            return (Criteria) this;
        }

        public Criteria andRalationshipIsNull() {
            addCriterion("ralationship is null");
            return (Criteria) this;
        }

        public Criteria andRalationshipIsNotNull() {
            addCriterion("ralationship is not null");
            return (Criteria) this;
        }

        public Criteria andRalationshipEqualTo(String value) {
            addCriterion("ralationship =", value, "ralationship");
            return (Criteria) this;
        }

        public Criteria andRalationshipNotEqualTo(String value) {
            addCriterion("ralationship <>", value, "ralationship");
            return (Criteria) this;
        }

        public Criteria andRalationshipGreaterThan(String value) {
            addCriterion("ralationship >", value, "ralationship");
            return (Criteria) this;
        }

        public Criteria andRalationshipGreaterThanOrEqualTo(String value) {
            addCriterion("ralationship >=", value, "ralationship");
            return (Criteria) this;
        }

        public Criteria andRalationshipLessThan(String value) {
            addCriterion("ralationship <", value, "ralationship");
            return (Criteria) this;
        }

        public Criteria andRalationshipLessThanOrEqualTo(String value) {
            addCriterion("ralationship <=", value, "ralationship");
            return (Criteria) this;
        }

        public Criteria andRalationshipLike(String value) {
            addCriterion("ralationship like", value, "ralationship");
            return (Criteria) this;
        }

        public Criteria andRalationshipNotLike(String value) {
            addCriterion("ralationship not like", value, "ralationship");
            return (Criteria) this;
        }

        public Criteria andRalationshipIn(List<String> values) {
            addCriterion("ralationship in", values, "ralationship");
            return (Criteria) this;
        }

        public Criteria andRalationshipNotIn(List<String> values) {
            addCriterion("ralationship not in", values, "ralationship");
            return (Criteria) this;
        }

        public Criteria andRalationshipBetween(String value1, String value2) {
            addCriterion("ralationship between", value1, value2, "ralationship");
            return (Criteria) this;
        }

        public Criteria andRalationshipNotBetween(String value1, String value2) {
            addCriterion("ralationship not between", value1, value2, "ralationship");
            return (Criteria) this;
        }

        public Criteria andInsuranceStartTimeIsNull() {
            addCriterion("insurance_start_time is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceStartTimeIsNotNull() {
            addCriterion("insurance_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceStartTimeEqualTo(String value) {
            addCriterion("insurance_start_time =", value, "insuranceStartTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceStartTimeNotEqualTo(String value) {
            addCriterion("insurance_start_time <>", value, "insuranceStartTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceStartTimeGreaterThan(String value) {
            addCriterion("insurance_start_time >", value, "insuranceStartTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("insurance_start_time >=", value, "insuranceStartTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceStartTimeLessThan(String value) {
            addCriterion("insurance_start_time <", value, "insuranceStartTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceStartTimeLessThanOrEqualTo(String value) {
            addCriterion("insurance_start_time <=", value, "insuranceStartTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceStartTimeLike(String value) {
            addCriterion("insurance_start_time like", value, "insuranceStartTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceStartTimeNotLike(String value) {
            addCriterion("insurance_start_time not like", value, "insuranceStartTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceStartTimeIn(List<String> values) {
            addCriterion("insurance_start_time in", values, "insuranceStartTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceStartTimeNotIn(List<String> values) {
            addCriterion("insurance_start_time not in", values, "insuranceStartTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceStartTimeBetween(String value1, String value2) {
            addCriterion("insurance_start_time between", value1, value2, "insuranceStartTime");
            return (Criteria) this;
        }

        public Criteria andInsuranceStartTimeNotBetween(String value1, String value2) {
            addCriterion("insurance_start_time not between", value1, value2, "insuranceStartTime");
            return (Criteria) this;
        }

        public Criteria andInsruanceEndTimeIsNull() {
            addCriterion("insruance_end_time is null");
            return (Criteria) this;
        }

        public Criteria andInsruanceEndTimeIsNotNull() {
            addCriterion("insruance_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andInsruanceEndTimeEqualTo(String value) {
            addCriterion("insruance_end_time =", value, "insruanceEndTime");
            return (Criteria) this;
        }

        public Criteria andInsruanceEndTimeNotEqualTo(String value) {
            addCriterion("insruance_end_time <>", value, "insruanceEndTime");
            return (Criteria) this;
        }

        public Criteria andInsruanceEndTimeGreaterThan(String value) {
            addCriterion("insruance_end_time >", value, "insruanceEndTime");
            return (Criteria) this;
        }

        public Criteria andInsruanceEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("insruance_end_time >=", value, "insruanceEndTime");
            return (Criteria) this;
        }

        public Criteria andInsruanceEndTimeLessThan(String value) {
            addCriterion("insruance_end_time <", value, "insruanceEndTime");
            return (Criteria) this;
        }

        public Criteria andInsruanceEndTimeLessThanOrEqualTo(String value) {
            addCriterion("insruance_end_time <=", value, "insruanceEndTime");
            return (Criteria) this;
        }

        public Criteria andInsruanceEndTimeLike(String value) {
            addCriterion("insruance_end_time like", value, "insruanceEndTime");
            return (Criteria) this;
        }

        public Criteria andInsruanceEndTimeNotLike(String value) {
            addCriterion("insruance_end_time not like", value, "insruanceEndTime");
            return (Criteria) this;
        }

        public Criteria andInsruanceEndTimeIn(List<String> values) {
            addCriterion("insruance_end_time in", values, "insruanceEndTime");
            return (Criteria) this;
        }

        public Criteria andInsruanceEndTimeNotIn(List<String> values) {
            addCriterion("insruance_end_time not in", values, "insruanceEndTime");
            return (Criteria) this;
        }

        public Criteria andInsruanceEndTimeBetween(String value1, String value2) {
            addCriterion("insruance_end_time between", value1, value2, "insruanceEndTime");
            return (Criteria) this;
        }

        public Criteria andInsruanceEndTimeNotBetween(String value1, String value2) {
            addCriterion("insruance_end_time not between", value1, value2, "insruanceEndTime");
            return (Criteria) this;
        }

        public Criteria andInsrncPeriodIsNull() {
            addCriterion("insrnc_period is null");
            return (Criteria) this;
        }

        public Criteria andInsrncPeriodIsNotNull() {
            addCriterion("insrnc_period is not null");
            return (Criteria) this;
        }

        public Criteria andInsrncPeriodEqualTo(Integer value) {
            addCriterion("insrnc_period =", value, "insrncPeriod");
            return (Criteria) this;
        }

        public Criteria andInsrncPeriodNotEqualTo(Integer value) {
            addCriterion("insrnc_period <>", value, "insrncPeriod");
            return (Criteria) this;
        }

        public Criteria andInsrncPeriodGreaterThan(Integer value) {
            addCriterion("insrnc_period >", value, "insrncPeriod");
            return (Criteria) this;
        }

        public Criteria andInsrncPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("insrnc_period >=", value, "insrncPeriod");
            return (Criteria) this;
        }

        public Criteria andInsrncPeriodLessThan(Integer value) {
            addCriterion("insrnc_period <", value, "insrncPeriod");
            return (Criteria) this;
        }

        public Criteria andInsrncPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("insrnc_period <=", value, "insrncPeriod");
            return (Criteria) this;
        }

        public Criteria andInsrncPeriodIn(List<Integer> values) {
            addCriterion("insrnc_period in", values, "insrncPeriod");
            return (Criteria) this;
        }

        public Criteria andInsrncPeriodNotIn(List<Integer> values) {
            addCriterion("insrnc_period not in", values, "insrncPeriod");
            return (Criteria) this;
        }

        public Criteria andInsrncPeriodBetween(Integer value1, Integer value2) {
            addCriterion("insrnc_period between", value1, value2, "insrncPeriod");
            return (Criteria) this;
        }

        public Criteria andInsrncPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("insrnc_period not between", value1, value2, "insrncPeriod");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andProdnoIsNull() {
            addCriterion("prodNo is null");
            return (Criteria) this;
        }

        public Criteria andProdnoIsNotNull() {
            addCriterion("prodNo is not null");
            return (Criteria) this;
        }

        public Criteria andProdnoEqualTo(String value) {
            addCriterion("prodNo =", value, "prodno");
            return (Criteria) this;
        }

        public Criteria andProdnoNotEqualTo(String value) {
            addCriterion("prodNo <>", value, "prodno");
            return (Criteria) this;
        }

        public Criteria andProdnoGreaterThan(String value) {
            addCriterion("prodNo >", value, "prodno");
            return (Criteria) this;
        }

        public Criteria andProdnoGreaterThanOrEqualTo(String value) {
            addCriterion("prodNo >=", value, "prodno");
            return (Criteria) this;
        }

        public Criteria andProdnoLessThan(String value) {
            addCriterion("prodNo <", value, "prodno");
            return (Criteria) this;
        }

        public Criteria andProdnoLessThanOrEqualTo(String value) {
            addCriterion("prodNo <=", value, "prodno");
            return (Criteria) this;
        }

        public Criteria andProdnoLike(String value) {
            addCriterion("prodNo like", value, "prodno");
            return (Criteria) this;
        }

        public Criteria andProdnoNotLike(String value) {
            addCriterion("prodNo not like", value, "prodno");
            return (Criteria) this;
        }

        public Criteria andProdnoIn(List<String> values) {
            addCriterion("prodNo in", values, "prodno");
            return (Criteria) this;
        }

        public Criteria andProdnoNotIn(List<String> values) {
            addCriterion("prodNo not in", values, "prodno");
            return (Criteria) this;
        }

        public Criteria andProdnoBetween(String value1, String value2) {
            addCriterion("prodNo between", value1, value2, "prodno");
            return (Criteria) this;
        }

        public Criteria andProdnoNotBetween(String value1, String value2) {
            addCriterion("prodNo not between", value1, value2, "prodno");
            return (Criteria) this;
        }

        public Criteria andProdtypeIsNull() {
            addCriterion("prodType is null");
            return (Criteria) this;
        }

        public Criteria andProdtypeIsNotNull() {
            addCriterion("prodType is not null");
            return (Criteria) this;
        }

        public Criteria andProdtypeEqualTo(String value) {
            addCriterion("prodType =", value, "prodtype");
            return (Criteria) this;
        }

        public Criteria andProdtypeNotEqualTo(String value) {
            addCriterion("prodType <>", value, "prodtype");
            return (Criteria) this;
        }

        public Criteria andProdtypeGreaterThan(String value) {
            addCriterion("prodType >", value, "prodtype");
            return (Criteria) this;
        }

        public Criteria andProdtypeGreaterThanOrEqualTo(String value) {
            addCriterion("prodType >=", value, "prodtype");
            return (Criteria) this;
        }

        public Criteria andProdtypeLessThan(String value) {
            addCriterion("prodType <", value, "prodtype");
            return (Criteria) this;
        }

        public Criteria andProdtypeLessThanOrEqualTo(String value) {
            addCriterion("prodType <=", value, "prodtype");
            return (Criteria) this;
        }

        public Criteria andProdtypeLike(String value) {
            addCriterion("prodType like", value, "prodtype");
            return (Criteria) this;
        }

        public Criteria andProdtypeNotLike(String value) {
            addCriterion("prodType not like", value, "prodtype");
            return (Criteria) this;
        }

        public Criteria andProdtypeIn(List<String> values) {
            addCriterion("prodType in", values, "prodtype");
            return (Criteria) this;
        }

        public Criteria andProdtypeNotIn(List<String> values) {
            addCriterion("prodType not in", values, "prodtype");
            return (Criteria) this;
        }

        public Criteria andProdtypeBetween(String value1, String value2) {
            addCriterion("prodType between", value1, value2, "prodtype");
            return (Criteria) this;
        }

        public Criteria andProdtypeNotBetween(String value1, String value2) {
            addCriterion("prodType not between", value1, value2, "prodtype");
            return (Criteria) this;
        }

        public Criteria andSumamountIsNull() {
            addCriterion("sumAmount is null");
            return (Criteria) this;
        }

        public Criteria andSumamountIsNotNull() {
            addCriterion("sumAmount is not null");
            return (Criteria) this;
        }

        public Criteria andSumamountEqualTo(String value) {
            addCriterion("sumAmount =", value, "sumamount");
            return (Criteria) this;
        }

        public Criteria andSumamountNotEqualTo(String value) {
            addCriterion("sumAmount <>", value, "sumamount");
            return (Criteria) this;
        }

        public Criteria andSumamountGreaterThan(String value) {
            addCriterion("sumAmount >", value, "sumamount");
            return (Criteria) this;
        }

        public Criteria andSumamountGreaterThanOrEqualTo(String value) {
            addCriterion("sumAmount >=", value, "sumamount");
            return (Criteria) this;
        }

        public Criteria andSumamountLessThan(String value) {
            addCriterion("sumAmount <", value, "sumamount");
            return (Criteria) this;
        }

        public Criteria andSumamountLessThanOrEqualTo(String value) {
            addCriterion("sumAmount <=", value, "sumamount");
            return (Criteria) this;
        }

        public Criteria andSumamountLike(String value) {
            addCriterion("sumAmount like", value, "sumamount");
            return (Criteria) this;
        }

        public Criteria andSumamountNotLike(String value) {
            addCriterion("sumAmount not like", value, "sumamount");
            return (Criteria) this;
        }

        public Criteria andSumamountIn(List<String> values) {
            addCriterion("sumAmount in", values, "sumamount");
            return (Criteria) this;
        }

        public Criteria andSumamountNotIn(List<String> values) {
            addCriterion("sumAmount not in", values, "sumamount");
            return (Criteria) this;
        }

        public Criteria andSumamountBetween(String value1, String value2) {
            addCriterion("sumAmount between", value1, value2, "sumamount");
            return (Criteria) this;
        }

        public Criteria andSumamountNotBetween(String value1, String value2) {
            addCriterion("sumAmount not between", value1, value2, "sumamount");
            return (Criteria) this;
        }

        public Criteria andSumpremiumIsNull() {
            addCriterion("sumPremium is null");
            return (Criteria) this;
        }

        public Criteria andSumpremiumIsNotNull() {
            addCriterion("sumPremium is not null");
            return (Criteria) this;
        }

        public Criteria andSumpremiumEqualTo(String value) {
            addCriterion("sumPremium =", value, "sumpremium");
            return (Criteria) this;
        }

        public Criteria andSumpremiumNotEqualTo(String value) {
            addCriterion("sumPremium <>", value, "sumpremium");
            return (Criteria) this;
        }

        public Criteria andSumpremiumGreaterThan(String value) {
            addCriterion("sumPremium >", value, "sumpremium");
            return (Criteria) this;
        }

        public Criteria andSumpremiumGreaterThanOrEqualTo(String value) {
            addCriterion("sumPremium >=", value, "sumpremium");
            return (Criteria) this;
        }

        public Criteria andSumpremiumLessThan(String value) {
            addCriterion("sumPremium <", value, "sumpremium");
            return (Criteria) this;
        }

        public Criteria andSumpremiumLessThanOrEqualTo(String value) {
            addCriterion("sumPremium <=", value, "sumpremium");
            return (Criteria) this;
        }

        public Criteria andSumpremiumLike(String value) {
            addCriterion("sumPremium like", value, "sumpremium");
            return (Criteria) this;
        }

        public Criteria andSumpremiumNotLike(String value) {
            addCriterion("sumPremium not like", value, "sumpremium");
            return (Criteria) this;
        }

        public Criteria andSumpremiumIn(List<String> values) {
            addCriterion("sumPremium in", values, "sumpremium");
            return (Criteria) this;
        }

        public Criteria andSumpremiumNotIn(List<String> values) {
            addCriterion("sumPremium not in", values, "sumpremium");
            return (Criteria) this;
        }

        public Criteria andSumpremiumBetween(String value1, String value2) {
            addCriterion("sumPremium between", value1, value2, "sumpremium");
            return (Criteria) this;
        }

        public Criteria andSumpremiumNotBetween(String value1, String value2) {
            addCriterion("sumPremium not between", value1, value2, "sumpremium");
            return (Criteria) this;
        }

        public Criteria andApplicationnoIsNull() {
            addCriterion("applicationNo is null");
            return (Criteria) this;
        }

        public Criteria andApplicationnoIsNotNull() {
            addCriterion("applicationNo is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationnoEqualTo(String value) {
            addCriterion("applicationNo =", value, "applicationno");
            return (Criteria) this;
        }

        public Criteria andApplicationnoNotEqualTo(String value) {
            addCriterion("applicationNo <>", value, "applicationno");
            return (Criteria) this;
        }

        public Criteria andApplicationnoGreaterThan(String value) {
            addCriterion("applicationNo >", value, "applicationno");
            return (Criteria) this;
        }

        public Criteria andApplicationnoGreaterThanOrEqualTo(String value) {
            addCriterion("applicationNo >=", value, "applicationno");
            return (Criteria) this;
        }

        public Criteria andApplicationnoLessThan(String value) {
            addCriterion("applicationNo <", value, "applicationno");
            return (Criteria) this;
        }

        public Criteria andApplicationnoLessThanOrEqualTo(String value) {
            addCriterion("applicationNo <=", value, "applicationno");
            return (Criteria) this;
        }

        public Criteria andApplicationnoLike(String value) {
            addCriterion("applicationNo like", value, "applicationno");
            return (Criteria) this;
        }

        public Criteria andApplicationnoNotLike(String value) {
            addCriterion("applicationNo not like", value, "applicationno");
            return (Criteria) this;
        }

        public Criteria andApplicationnoIn(List<String> values) {
            addCriterion("applicationNo in", values, "applicationno");
            return (Criteria) this;
        }

        public Criteria andApplicationnoNotIn(List<String> values) {
            addCriterion("applicationNo not in", values, "applicationno");
            return (Criteria) this;
        }

        public Criteria andApplicationnoBetween(String value1, String value2) {
            addCriterion("applicationNo between", value1, value2, "applicationno");
            return (Criteria) this;
        }

        public Criteria andApplicationnoNotBetween(String value1, String value2) {
            addCriterion("applicationNo not between", value1, value2, "applicationno");
            return (Criteria) this;
        }

        public Criteria andPolicynoIsNull() {
            addCriterion("policyNo is null");
            return (Criteria) this;
        }

        public Criteria andPolicynoIsNotNull() {
            addCriterion("policyNo is not null");
            return (Criteria) this;
        }

        public Criteria andPolicynoEqualTo(String value) {
            addCriterion("policyNo =", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoNotEqualTo(String value) {
            addCriterion("policyNo <>", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoGreaterThan(String value) {
            addCriterion("policyNo >", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoGreaterThanOrEqualTo(String value) {
            addCriterion("policyNo >=", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoLessThan(String value) {
            addCriterion("policyNo <", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoLessThanOrEqualTo(String value) {
            addCriterion("policyNo <=", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoLike(String value) {
            addCriterion("policyNo like", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoNotLike(String value) {
            addCriterion("policyNo not like", value, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoIn(List<String> values) {
            addCriterion("policyNo in", values, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoNotIn(List<String> values) {
            addCriterion("policyNo not in", values, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoBetween(String value1, String value2) {
            addCriterion("policyNo between", value1, value2, "policyno");
            return (Criteria) this;
        }

        public Criteria andPolicynoNotBetween(String value1, String value2) {
            addCriterion("policyNo not between", value1, value2, "policyno");
            return (Criteria) this;
        }

        public Criteria andPayUrlIsNull() {
            addCriterion("pay_url is null");
            return (Criteria) this;
        }

        public Criteria andPayUrlIsNotNull() {
            addCriterion("pay_url is not null");
            return (Criteria) this;
        }

        public Criteria andPayUrlEqualTo(String value) {
            addCriterion("pay_url =", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlNotEqualTo(String value) {
            addCriterion("pay_url <>", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlGreaterThan(String value) {
            addCriterion("pay_url >", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pay_url >=", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlLessThan(String value) {
            addCriterion("pay_url <", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlLessThanOrEqualTo(String value) {
            addCriterion("pay_url <=", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlLike(String value) {
            addCriterion("pay_url like", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlNotLike(String value) {
            addCriterion("pay_url not like", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlIn(List<String> values) {
            addCriterion("pay_url in", values, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlNotIn(List<String> values) {
            addCriterion("pay_url not in", values, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlBetween(String value1, String value2) {
            addCriterion("pay_url between", value1, value2, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlNotBetween(String value1, String value2) {
            addCriterion("pay_url not between", value1, value2, "payUrl");
            return (Criteria) this;
        }

        public Criteria andCarnoIsNull() {
            addCriterion("carNo is null");
            return (Criteria) this;
        }

        public Criteria andCarnoIsNotNull() {
            addCriterion("carNo is not null");
            return (Criteria) this;
        }

        public Criteria andCarnoEqualTo(String value) {
            addCriterion("carNo =", value, "carno");
            return (Criteria) this;
        }

        public Criteria andCarnoNotEqualTo(String value) {
            addCriterion("carNo <>", value, "carno");
            return (Criteria) this;
        }

        public Criteria andCarnoGreaterThan(String value) {
            addCriterion("carNo >", value, "carno");
            return (Criteria) this;
        }

        public Criteria andCarnoGreaterThanOrEqualTo(String value) {
            addCriterion("carNo >=", value, "carno");
            return (Criteria) this;
        }

        public Criteria andCarnoLessThan(String value) {
            addCriterion("carNo <", value, "carno");
            return (Criteria) this;
        }

        public Criteria andCarnoLessThanOrEqualTo(String value) {
            addCriterion("carNo <=", value, "carno");
            return (Criteria) this;
        }

        public Criteria andCarnoLike(String value) {
            addCriterion("carNo like", value, "carno");
            return (Criteria) this;
        }

        public Criteria andCarnoNotLike(String value) {
            addCriterion("carNo not like", value, "carno");
            return (Criteria) this;
        }

        public Criteria andCarnoIn(List<String> values) {
            addCriterion("carNo in", values, "carno");
            return (Criteria) this;
        }

        public Criteria andCarnoNotIn(List<String> values) {
            addCriterion("carNo not in", values, "carno");
            return (Criteria) this;
        }

        public Criteria andCarnoBetween(String value1, String value2) {
            addCriterion("carNo between", value1, value2, "carno");
            return (Criteria) this;
        }

        public Criteria andCarnoNotBetween(String value1, String value2) {
            addCriterion("carNo not between", value1, value2, "carno");
            return (Criteria) this;
        }

        public Criteria andInsuredrivenoIsNull() {
            addCriterion("insureDriveNo is null");
            return (Criteria) this;
        }

        public Criteria andInsuredrivenoIsNotNull() {
            addCriterion("insureDriveNo is not null");
            return (Criteria) this;
        }

        public Criteria andInsuredrivenoEqualTo(String value) {
            addCriterion("insureDriveNo =", value, "insuredriveno");
            return (Criteria) this;
        }

        public Criteria andInsuredrivenoNotEqualTo(String value) {
            addCriterion("insureDriveNo <>", value, "insuredriveno");
            return (Criteria) this;
        }

        public Criteria andInsuredrivenoGreaterThan(String value) {
            addCriterion("insureDriveNo >", value, "insuredriveno");
            return (Criteria) this;
        }

        public Criteria andInsuredrivenoGreaterThanOrEqualTo(String value) {
            addCriterion("insureDriveNo >=", value, "insuredriveno");
            return (Criteria) this;
        }

        public Criteria andInsuredrivenoLessThan(String value) {
            addCriterion("insureDriveNo <", value, "insuredriveno");
            return (Criteria) this;
        }

        public Criteria andInsuredrivenoLessThanOrEqualTo(String value) {
            addCriterion("insureDriveNo <=", value, "insuredriveno");
            return (Criteria) this;
        }

        public Criteria andInsuredrivenoLike(String value) {
            addCriterion("insureDriveNo like", value, "insuredriveno");
            return (Criteria) this;
        }

        public Criteria andInsuredrivenoNotLike(String value) {
            addCriterion("insureDriveNo not like", value, "insuredriveno");
            return (Criteria) this;
        }

        public Criteria andInsuredrivenoIn(List<String> values) {
            addCriterion("insureDriveNo in", values, "insuredriveno");
            return (Criteria) this;
        }

        public Criteria andInsuredrivenoNotIn(List<String> values) {
            addCriterion("insureDriveNo not in", values, "insuredriveno");
            return (Criteria) this;
        }

        public Criteria andInsuredrivenoBetween(String value1, String value2) {
            addCriterion("insureDriveNo between", value1, value2, "insuredriveno");
            return (Criteria) this;
        }

        public Criteria andInsuredrivenoNotBetween(String value1, String value2) {
            addCriterion("insureDriveNo not between", value1, value2, "insuredriveno");
            return (Criteria) this;
        }

        public Criteria andTgtFld11IsNull() {
            addCriterion("tgt_fld11 is null");
            return (Criteria) this;
        }

        public Criteria andTgtFld11IsNotNull() {
            addCriterion("tgt_fld11 is not null");
            return (Criteria) this;
        }

        public Criteria andTgtFld11EqualTo(Integer value) {
            addCriterion("tgt_fld11 =", value, "tgtFld11");
            return (Criteria) this;
        }

        public Criteria andTgtFld11NotEqualTo(Integer value) {
            addCriterion("tgt_fld11 <>", value, "tgtFld11");
            return (Criteria) this;
        }

        public Criteria andTgtFld11GreaterThan(Integer value) {
            addCriterion("tgt_fld11 >", value, "tgtFld11");
            return (Criteria) this;
        }

        public Criteria andTgtFld11GreaterThanOrEqualTo(Integer value) {
            addCriterion("tgt_fld11 >=", value, "tgtFld11");
            return (Criteria) this;
        }

        public Criteria andTgtFld11LessThan(Integer value) {
            addCriterion("tgt_fld11 <", value, "tgtFld11");
            return (Criteria) this;
        }

        public Criteria andTgtFld11LessThanOrEqualTo(Integer value) {
            addCriterion("tgt_fld11 <=", value, "tgtFld11");
            return (Criteria) this;
        }

        public Criteria andTgtFld11In(List<Integer> values) {
            addCriterion("tgt_fld11 in", values, "tgtFld11");
            return (Criteria) this;
        }

        public Criteria andTgtFld11NotIn(List<Integer> values) {
            addCriterion("tgt_fld11 not in", values, "tgtFld11");
            return (Criteria) this;
        }

        public Criteria andTgtFld11Between(Integer value1, Integer value2) {
            addCriterion("tgt_fld11 between", value1, value2, "tgtFld11");
            return (Criteria) this;
        }

        public Criteria andTgtFld11NotBetween(Integer value1, Integer value2) {
            addCriterion("tgt_fld11 not between", value1, value2, "tgtFld11");
            return (Criteria) this;
        }

        public Criteria andTgtFld12IsNull() {
            addCriterion("tgt_fld12 is null");
            return (Criteria) this;
        }

        public Criteria andTgtFld12IsNotNull() {
            addCriterion("tgt_fld12 is not null");
            return (Criteria) this;
        }

        public Criteria andTgtFld12EqualTo(String value) {
            addCriterion("tgt_fld12 =", value, "tgtFld12");
            return (Criteria) this;
        }

        public Criteria andTgtFld12NotEqualTo(String value) {
            addCriterion("tgt_fld12 <>", value, "tgtFld12");
            return (Criteria) this;
        }

        public Criteria andTgtFld12GreaterThan(String value) {
            addCriterion("tgt_fld12 >", value, "tgtFld12");
            return (Criteria) this;
        }

        public Criteria andTgtFld12GreaterThanOrEqualTo(String value) {
            addCriterion("tgt_fld12 >=", value, "tgtFld12");
            return (Criteria) this;
        }

        public Criteria andTgtFld12LessThan(String value) {
            addCriterion("tgt_fld12 <", value, "tgtFld12");
            return (Criteria) this;
        }

        public Criteria andTgtFld12LessThanOrEqualTo(String value) {
            addCriterion("tgt_fld12 <=", value, "tgtFld12");
            return (Criteria) this;
        }

        public Criteria andTgtFld12Like(String value) {
            addCriterion("tgt_fld12 like", value, "tgtFld12");
            return (Criteria) this;
        }

        public Criteria andTgtFld12NotLike(String value) {
            addCriterion("tgt_fld12 not like", value, "tgtFld12");
            return (Criteria) this;
        }

        public Criteria andTgtFld12In(List<String> values) {
            addCriterion("tgt_fld12 in", values, "tgtFld12");
            return (Criteria) this;
        }

        public Criteria andTgtFld12NotIn(List<String> values) {
            addCriterion("tgt_fld12 not in", values, "tgtFld12");
            return (Criteria) this;
        }

        public Criteria andTgtFld12Between(String value1, String value2) {
            addCriterion("tgt_fld12 between", value1, value2, "tgtFld12");
            return (Criteria) this;
        }

        public Criteria andTgtFld12NotBetween(String value1, String value2) {
            addCriterion("tgt_fld12 not between", value1, value2, "tgtFld12");
            return (Criteria) this;
        }

        public Criteria andTgtFld16IsNull() {
            addCriterion("tgt_fld16 is null");
            return (Criteria) this;
        }

        public Criteria andTgtFld16IsNotNull() {
            addCriterion("tgt_fld16 is not null");
            return (Criteria) this;
        }

        public Criteria andTgtFld16EqualTo(String value) {
            addCriterion("tgt_fld16 =", value, "tgtFld16");
            return (Criteria) this;
        }

        public Criteria andTgtFld16NotEqualTo(String value) {
            addCriterion("tgt_fld16 <>", value, "tgtFld16");
            return (Criteria) this;
        }

        public Criteria andTgtFld16GreaterThan(String value) {
            addCriterion("tgt_fld16 >", value, "tgtFld16");
            return (Criteria) this;
        }

        public Criteria andTgtFld16GreaterThanOrEqualTo(String value) {
            addCriterion("tgt_fld16 >=", value, "tgtFld16");
            return (Criteria) this;
        }

        public Criteria andTgtFld16LessThan(String value) {
            addCriterion("tgt_fld16 <", value, "tgtFld16");
            return (Criteria) this;
        }

        public Criteria andTgtFld16LessThanOrEqualTo(String value) {
            addCriterion("tgt_fld16 <=", value, "tgtFld16");
            return (Criteria) this;
        }

        public Criteria andTgtFld16Like(String value) {
            addCriterion("tgt_fld16 like", value, "tgtFld16");
            return (Criteria) this;
        }

        public Criteria andTgtFld16NotLike(String value) {
            addCriterion("tgt_fld16 not like", value, "tgtFld16");
            return (Criteria) this;
        }

        public Criteria andTgtFld16In(List<String> values) {
            addCriterion("tgt_fld16 in", values, "tgtFld16");
            return (Criteria) this;
        }

        public Criteria andTgtFld16NotIn(List<String> values) {
            addCriterion("tgt_fld16 not in", values, "tgtFld16");
            return (Criteria) this;
        }

        public Criteria andTgtFld16Between(String value1, String value2) {
            addCriterion("tgt_fld16 between", value1, value2, "tgtFld16");
            return (Criteria) this;
        }

        public Criteria andTgtFld16NotBetween(String value1, String value2) {
            addCriterion("tgt_fld16 not between", value1, value2, "tgtFld16");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}