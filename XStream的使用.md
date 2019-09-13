```
XStream的使用
```

## 一、特性

​	XStream目前提供JSON支持和变形。

demo:

```java
XStream xStream = new XStream();
//下面是为类设置安全策略，只有xml 转 实体类的时候需要
XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{Friend.class,Person.class});
 //todo:如果不添加下面的配置，结果是类的全限定名，而不是类名
 xStream.alias("person", Person.class);
 xStream.alias("friend", Friend.class);

//todo: 将对象序列化为XML
 String xml = xStream.toXML(person);

//todo:从XML反序列化对象
Person dePerson = (Person) xStream.fromXML(xml);

```

## 二、别名

###### 类起别名：

```
 xStream.alias("person", Person.class);
```

###### 字段别名： 

```
xStream.aliasField("name", Person.class, "username");
```

隐藏集合名称：不显示类中集合字段名称、也可以重命名集合名称

Array或Map也可以声明为隐式。

```java
xStream.addImplicitCollection(Person.class, "friendList");
```

```xml
<person>
  <username>meow</username>
  <password>meow1234</password>
  <age>18</age>
  <money>10000.01</money>
  <friend>
    <name>张志斌</name>
    <email>zhang4111@qq.com</email>
  </friend>
  <friend>
    <name>赵帅</name>
    <email>zhao@163.com</email>
  </friend>
  <friend/>
</person>
```

```xml
<person>
  <username>meow</username>
  <password>meow1234</password>
  <age>18</age>
  <money>10000.01</money>
  <friendList>
    <friend>
      <name>张志斌</name>
      <email>zhang4111@qq.com</email>
    </friend>
    <friend>
      <name>赵帅</name>
      <email>zhao@163.com</email>
    </friend>
    <friend/>
  </friendList>
</person>
```

###### 类属性

将Contact单string类型的属性，作为Person类的一个属性 

```java
@Data
public class Person {
  private String username;
  private String password;
  private Integer age;
  private double money;

  private Contact contact; //单String属性

  private List<Friend> friendList;
  
}
```

```xml
结果：
<person contact="17610773515">
  <username>meow</username>
  <password>meow1234</password>
  <age>18</age>
  <money>10000.01</money>
  <friendList>
    <friend>
      <name>张志斌</name>
      <email>zhang4111@qq.com</email>
    </friend>
    <friend>
      <name>赵帅</name>
      <email>zhao@163.com</email>
    </friend>
  </friendList>
</person>
```

实现方式：

```java
xStream.registerConverter(new ContactConverter());
xStream.useAttributeFor(Person.class,"contact");
```

```java
import com.example.demo.domain.Contact;
import com.thoughtworks.xstream.converters.SingleValueConverter;
/**
*各种类型转换器
*http://x-stream.github.io/converters.html
*/
public class ContactConverter implements SingleValueConverter {
    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(Contact.class);
    }

    @Override
    public String toString(Object obj) {
        return ((Contact) obj).getMobile();
    }

    @Override
    public Object fromString(String email) {
        return new Contact(email);
    }
}
```

包名 别名：Package aliasing

```java
xstream.aliasPackage("my.company", "org.thoughtworks");
```

## 三、忽略属性（Omitted Fields）

```java
xStream.omitField(Person.class,"age"); //也适用于类属性
```

```java
<person contact="17610773515">
  <username>meow</username>
  <password>meow1234</password>
  //age属性被忽略
  <money>10000.01</money>
  <friendList>
    <friend>
      <name>张志斌</name>
      <email>zhang4111@qq.com</email>
    </friend>
    <friend>
      <name>赵帅</name>
      <email>zhao@163.com</email>
    </friend>
  </friendList>
</person>
```

Json格式支持

```java
XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
```

```json
{"person": {
  "id": 0,
  "username": "meow",
  "password": "meow1234",
  "age": 18,
  "money": 10000.01,
  "friendList": [
    {
      "name": "张志斌",
      "email": "zhang4111@qq.com"
    },
    {
      "name": "赵帅",
      "email": "zhao@163.com"
    }
  ]
}}
```

```java
//删除Json的根节点
XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
    public HierarchicalStreamWriter createWriter(Writer writer) {
        return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
    }
});

结果
{
  "id": 0,
  "username": "meow",
  "password": "meow1234",
  "age": 18,
  "money": 10000.01,
  "friendList": [
    {
      "name": "张志斌",
      "email": "zhang4111@qq.com"
    },
    {
      "name": "赵帅",
      "email": "zhao@163.com"
    }
  ]
}
```

四、类型转换

单值转换

```java
package com.example.demo.converter;

import com.example.demo.domain.Contact;
import com.thoughtworks.xstream.converters.SingleValueConverter;

public class ContactConverter implements SingleValueConverter {
    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(Contact.class);
    }

    @Override
    public String toString(Object obj) {
        return ((Contact) obj).getMobile();
    }

    @Override
    public Object fromString(String s) {
        return new Contact(s);
    }
}
```

日期转换

```java
package com.example.demo.converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateConverter implements Converter {
    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext marshallingContext) {
        XMLGregorianCalendar calendar = (XMLGregorianCalendar) source;
        writer.setValue(calendar.toString());
    }

    /***将文本数据转换回对象*/
    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        String value = reader.getValue();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(value);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            DatatypeFactory dtf = null;
            dtf = DatatypeFactory.newInstance();
            XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();
            dateType.setYear(cal.get(Calendar.YEAR));
            //由于Calendar.MONTH取值范围为0~11,需要加1
            dateType.setMonth(cal.get(Calendar.MONTH) + 1);
            dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));
            dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));
            dateType.setMinute(cal.get(Calendar.MINUTE));
            dateType.setSecond(cal.get(Calendar.SECOND));

            return dateType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean canConvert(Class aClass) {
        if (XMLGregorianCalendar.class.isAssignableFrom(aClass)) {
            return Boolean.TRUE;
        }
        return false;
    }
}
```

