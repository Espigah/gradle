import com.test.SimpleAnnotation

@SimpleAnnotation
class Person {
    String name
    int age

    void sing() {
        Bad code = new thatDoesntAffectStubGeneration()
        println "tra-la-la"
    }
}