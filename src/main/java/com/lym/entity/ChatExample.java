package com.lym.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChatExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFromUserIdIsNull() {
            addCriterion("from_user_id is null");
            return (Criteria) this;
        }

        public Criteria andFromUserIdIsNotNull() {
            addCriterion("from_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andFromUserIdEqualTo(Long value) {
            addCriterion("from_user_id =", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotEqualTo(Long value) {
            addCriterion("from_user_id <>", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdGreaterThan(Long value) {
            addCriterion("from_user_id >", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("from_user_id >=", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdLessThan(Long value) {
            addCriterion("from_user_id <", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdLessThanOrEqualTo(Long value) {
            addCriterion("from_user_id <=", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdIn(List<Long> values) {
            addCriterion("from_user_id in", values, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotIn(List<Long> values) {
            addCriterion("from_user_id not in", values, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdBetween(Long value1, Long value2) {
            addCriterion("from_user_id between", value1, value2, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotBetween(Long value1, Long value2) {
            addCriterion("from_user_id not between", value1, value2, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdIsNull() {
            addCriterion("to_user_id is null");
            return (Criteria) this;
        }

        public Criteria andToUserIdIsNotNull() {
            addCriterion("to_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andToUserIdEqualTo(Long value) {
            addCriterion("to_user_id =", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotEqualTo(Long value) {
            addCriterion("to_user_id <>", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdGreaterThan(Long value) {
            addCriterion("to_user_id >", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("to_user_id >=", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdLessThan(Long value) {
            addCriterion("to_user_id <", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdLessThanOrEqualTo(Long value) {
            addCriterion("to_user_id <=", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdIn(List<Long> values) {
            addCriterion("to_user_id in", values, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotIn(List<Long> values) {
            addCriterion("to_user_id not in", values, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdBetween(Long value1, Long value2) {
            addCriterion("to_user_id between", value1, value2, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotBetween(Long value1, Long value2) {
            addCriterion("to_user_id not between", value1, value2, "toUserId");
            return (Criteria) this;
        }

        public Criteria andMsgIsNull() {
            addCriterion("msg is null");
            return (Criteria) this;
        }

        public Criteria andMsgIsNotNull() {
            addCriterion("msg is not null");
            return (Criteria) this;
        }

        public Criteria andMsgEqualTo(String value) {
            addCriterion("msg =", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotEqualTo(String value) {
            addCriterion("msg <>", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThan(String value) {
            addCriterion("msg >", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThanOrEqualTo(String value) {
            addCriterion("msg >=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThan(String value) {
            addCriterion("msg <", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThanOrEqualTo(String value) {
            addCriterion("msg <=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLike(String value) {
            addCriterion("msg like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotLike(String value) {
            addCriterion("msg not like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgIn(List<String> values) {
            addCriterion("msg in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotIn(List<String> values) {
            addCriterion("msg not in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgBetween(String value1, String value2) {
            addCriterion("msg between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotBetween(String value1, String value2) {
            addCriterion("msg not between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andReadStateIsNull() {
            addCriterion("read_state is null");
            return (Criteria) this;
        }

        public Criteria andReadStateIsNotNull() {
            addCriterion("read_state is not null");
            return (Criteria) this;
        }

        public Criteria andReadStateEqualTo(Byte value) {
            addCriterion("read_state =", value, "readState");
            return (Criteria) this;
        }

        public Criteria andReadStateNotEqualTo(Byte value) {
            addCriterion("read_state <>", value, "readState");
            return (Criteria) this;
        }

        public Criteria andReadStateGreaterThan(Byte value) {
            addCriterion("read_state >", value, "readState");
            return (Criteria) this;
        }

        public Criteria andReadStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("read_state >=", value, "readState");
            return (Criteria) this;
        }

        public Criteria andReadStateLessThan(Byte value) {
            addCriterion("read_state <", value, "readState");
            return (Criteria) this;
        }

        public Criteria andReadStateLessThanOrEqualTo(Byte value) {
            addCriterion("read_state <=", value, "readState");
            return (Criteria) this;
        }

        public Criteria andReadStateIn(List<Byte> values) {
            addCriterion("read_state in", values, "readState");
            return (Criteria) this;
        }

        public Criteria andReadStateNotIn(List<Byte> values) {
            addCriterion("read_state not in", values, "readState");
            return (Criteria) this;
        }

        public Criteria andReadStateBetween(Byte value1, Byte value2) {
            addCriterion("read_state between", value1, value2, "readState");
            return (Criteria) this;
        }

        public Criteria andReadStateNotBetween(Byte value1, Byte value2) {
            addCriterion("read_state not between", value1, value2, "readState");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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