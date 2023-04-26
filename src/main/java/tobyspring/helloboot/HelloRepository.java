package tobyspring.helloboot;

public interface HelloRepository {
    // DB에 인자로 받는 name이 존재하면 Hello를 반환
    Hello findHello(String name);

    // DB에 인자로 받는 name에 count를 1 증가시크는 메서드
    void increaseCount(String name);


    // default 메서드가 멀까? : default 메소드는 인터페이스에 기능만 선언할 수 있는 상황을 넘어 로직을 구성할 수 있는 메소드를 말한다.
    // name에 대한 count를 반환하는
    default int countOf(String name){
        Hello hello = findHello(name);
        return hello == null? 0 : hello.getCount();
    }
}
