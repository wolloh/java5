import Annotation.AutoInjectable;

import java.lang.reflect.InvocationTargetException;

interface SomeInterface{
    public void doSomething();
}
interface SomeOtherInterface{
    public void doSomeOther();
}
class SomeImpl implements SomeInterface{
    public void doSomething(){ System.out.println("A");}
}
class OtherImpl implements SomeInterface{
    public void doSomething(){ System.out.println("B");}
}
class SODoer implements SomeOtherInterface{
    public void doSomeOther(){ System.out.println("C");}
}

class SomeBean{
    @AutoInjectable
    private SomeInterface field1;
    @AutoInjectable
    private SomeOtherInterface field2;

    public void foo(){
        field1.doSomething();
        field2.doSomeOther();
    }
}

public class Laba5 {
    public static void main(String[] args)  {
         String PATH_TO_PROPERTIES = "resources/props.properties";
       // Injector injector=new Injector(PATH_TO_PROPERTIES);

       SomeBean a = (new Injector(PATH_TO_PROPERTIES)).inject(new SomeBean());
        a.foo();
    }
}