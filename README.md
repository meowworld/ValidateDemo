# ValidateDemo
@Validate 分组校验demo


下面是补充方法：以前的校信息提示不好
  
  public static void validEntity(Object bean){

        Object objectToValidate = AopProxyUtils.getSingletonTarget(bean);
        if (objectToValidate == null) {
            objectToValidate = bean;
        }
        Set<ConstraintViolation<Object>> result = validator.validate(objectToValidate);

        if (!result.isEmpty()) {
            StringBuilder sb = new StringBuilder("Bean state is invalid: ");
            for (Iterator<ConstraintViolation<Object>> it = result.iterator(); it.hasNext();) {
                ConstraintViolation<Object> violation = it.next();
                sb.append(violation.getPropertyPath()).append(" - ").append(violation.getMessage());
                if (it.hasNext()) {
                    sb.append("; ");
                }
            }
            throw new BeanInitializationException(sb.toString());
        }
    }
