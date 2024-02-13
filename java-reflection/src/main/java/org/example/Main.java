package org.example;

//Java Reflection provides ability to inspect and modify the runtime behavior of application.

import java.lang.reflect.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        //Get Class Object
        // Get Class using reflection
        Class<?> concreteClass = ConcreteClass.class;
        concreteClass = new ConcreteClass(5).getClass();
        try {
            // below method is used most of the times in frameworks like JUnit
            //Spring dependency injection, Tomcat web container
            //Eclipse auto completion of method names, hibernate, Struts2 etc.
            //because ConcreteClass is not available at compile time
            concreteClass = Class.forName("org.example.ConcreteClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(concreteClass.getCanonicalName()); // prints org.example.ConcreteClass

//for primitive types, wrapper classes and arrays
        Class<?> booleanClass = boolean.class;
        System.out.println(booleanClass.getCanonicalName()); // prints boolean

        Class<?> cDouble = Double.TYPE;
        System.out.println(cDouble.getCanonicalName()); // prints double

        Class<?> cDoubleArray = Class.forName("[D");
        System.out.println(cDoubleArray.getCanonicalName()); //prints double[]

        Class<?> twoDStringArray = String[][].class;
        System.out.println(twoDStringArray.getCanonicalName()); // prints java.lang.String[][]

//Get Super Class
        Class<?> superClass = Class.forName("org.example.ConcreteClass").getSuperclass();
        System.out.println(superClass); // prints "class org.example.BaseClass"
        System.out.println(Object.class.getSuperclass()); // prints "null"
        System.out.println(String[][].class.getSuperclass());// prints "class java.lang.Object"

//Get Public Member Classes
        Class<?>[] classes = concreteClass.getClasses();
        //[class com.journaldev.reflection.ConcreteClass$ConcreteClassPublicClass,
        //class com.journaldev.reflection.ConcreteClass$ConcreteClassPublicEnum,
        //interface com.journaldev.reflection.ConcreteClass$ConcreteClassPublicInterface,
        //class com.journaldev.reflection.BaseClass$BaseClassInnerClass,
        //class com.journaldev.reflection.BaseClass$BaseClassMemberEnum]
        System.out.println(Arrays.toString(classes));

//Get Declared Classes

        //getting all of the classes, interfaces, and enums that are explicitly declared in ConcreteClass
        Class<?>[] explicitClasses = Class.forName("org.example.ConcreteClass").getDeclaredClasses();
        //prints [class org.example.ConcreteClass$ConcreteClassDefaultClass,
        //class org.example.reflection.ConcreteClass$ConcreteClassDefaultEnum,
        //class org.example.ConcreteClass$ConcreteClassPrivateClass,
        //class org.example.ConcreteClass$ConcreteClassProtectedClass,
        //class org.example.ConcreteClass$ConcreteClassPublicClass,
        //class org.example.ConcreteClass$ConcreteClassPublicEnum,
        //interface org.example.ConcreteClass$ConcreteClassPublicInterface]
        System.out.println(Arrays.toString(explicitClasses));

//Get Declaring Class
        Class<?> innerClass = Class.forName("org.example.ConcreteClass$ConcreteClassDefaultClass");
        //prints org.example.ConcreteClass
        System.out.println(innerClass.getDeclaringClass().getCanonicalName());
        System.out.println(innerClass.getEnclosingClass().getCanonicalName());

 //Getting Package Name
        //prints "org.example"
        System.out.println(Class.forName("org.example.BaseInterface").getPackage().getName());

//Getting Class Modifiers
        System.out.println(Modifier.toString(concreteClass.getModifiers())); //prints "public"
        //prints "public abstract interface"
        System.out.println(Modifier.toString(Class.forName("org.example.BaseInterface").getModifiers()));

//Get Type parameters (generics)
        TypeVariable<?>[] typeParameters = Class.forName("java.util.HashMap").getTypeParameters();
        for(TypeVariable<?> t : typeParameters)
            System.out.print(t.getName()+",");

//Get Implemented Interfaces
        Type[] interfaces = Class.forName("java.util.HashMap").getGenericInterfaces();
        //prints "[java.util.Map<K, V>, interface java.lang.Cloneable, interface java.io.Serializable]"
        System.out.println(Arrays.toString(interfaces));
        //prints "[interface java.util.Map, interface java.lang.Cloneable, interface java.io.Serializable]"
        System.out.println(Arrays.toString(Class.forName("java.util.HashMap").getInterfaces()));

//Get All Public Methods
        Method[] publicMethods = Class.forName("org.example.ConcreteClass").getMethods();
        //prints public methods of ConcreteClass, BaseClass, Object
        System.out.println(Arrays.toString(publicMethods));

//Get All public constructors
        Constructor<?>[] publicConstructors = Class.forName("org.example.ConcreteClass").getConstructors();
        //prints public constructors of ConcreteClass
        System.out.println(Arrays.toString(publicConstructors));

//Get All public fields
        Field[] publicFields = Class.forName("org.example.ConcreteClass").getFields();
        //prints public fields of ConcreteClass, it's superclass and super interfaces
        System.out.println(Arrays.toString(publicFields));

    }
}

// Some of the frameworks that use java reflection are:
//
//JUnit - uses reflection to parse @Test annotation to get the test methods and then invoke it.
//Spring - dependency injection, read more at Spring Dependency Injection
//Tomcat web container to forward the request to correct module by parsing their web.xml files and request URI.
//Eclipse auto completion of method names
//Struts
//Hibernate

// We should not use reflection in normal programming where we already have access to the classes and interfaces because of following drawbacks.
//
//Poor Performance - Since java reflection resolve the types dynamically, it involves processing like scanning the classpath to find the class to load, causing slow performance.
//Security Restrictions - Reflection requires runtime permissions that might not be available for system running under security manager. This can cause you application to fail at runtime because of security manager.
//Security Issues - Using reflection we can access part of code that we are not supposed to access, for example we can access private fields of a class and change it’s value. This can be a serious security threat and cause your application to behave abnormally.
//High Maintenance

// java.lang.Class is the entry point for all the reflection operations