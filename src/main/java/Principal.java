import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class Principal {
    final Integer MAX_WEIGHT_SUM = 100;

    final private Map<Integer, List<Pair<Teacher, Boolean>>> allYearsTeachers = Map.ofEntries(
        new AbstractMap.SimpleImmutableEntry<>(
            2020,
            List.of(
                    new Pair<>( new ProfesorTC(1,"Josefina"), true),
                    new Pair<>( new ProfesorTC(1,"Edonisio"), true),
                    new Pair<>( new ProfesorTC(1,"Edufasio"), false)
            )
        ),
        new AbstractMap.SimpleImmutableEntry<>(
                2019,
                List.of(
                        new Pair<>( new ProfesorTC(1,"Eduarda"), false),
                        new Pair<>( new ProfesorTC(1,"Abelardo"), false),
                        new Pair<>( new ProfesorTC(1,"Francisca"), false)
                )
        ),
        new AbstractMap.SimpleImmutableEntry<>(
                2018,
                List.of(
                        new Pair<>( new ProfesorTP(0,"Alvaro"), true),
                        new Pair<>( new ProfesorTC(1,"Jonathan"), true),
                        new Pair<>( new Administrativo(2,"Maor"), true)
                )
        )
    );
    private final int yearToCalculate;

    public Principal(int yearToCalculate) {
        this.yearToCalculate = yearToCalculate;
    }
    public float calculateGrades(final List<Pair<Integer, Float>> examsStudents, final boolean hasReachedMinimumClasses) {
        if (examsStudents.isEmpty()) {
            return 0f;
        }
        float gradesSum       = getGradesSum(examsStudents);
        int   gradesWeightSum = getGradesWeightSum(examsStudents);

        return gradeVerification(hasReachedMinimumClasses, gradesSum, gradesWeightSum);
    }

    private int getGradesWeightSum(List<Pair<Integer, Float>> examsStudents) {
        int gradesWeightSum = 0;
        for (Pair<Integer, Float> examGrade : examsStudents) {
            gradesWeightSum += examGrade.first();
        }
        return gradesWeightSum;
    }

    private float getGradesSum(List<Pair<Integer, Float>> examsStudents) {
        float gradesSum = 0f;
        for (Pair<Integer, Float> examGrade : examsStudents) {
            gradesSum += (examGrade.first() * examGrade.second() / MAX_WEIGHT_SUM);
        }
        return gradesSum;
    }


    private float gradeVerification(boolean hasReachedMinimumClasses, float gradesSum, int gradesWeightSum) {
        if (gradesWeightSum > MAX_WEIGHT_SUM){
            return -1f;
        }
        
        if (gradesWeightSum == MAX_WEIGHT_SUM ){ 
            if (hasReachedMinimumClasses) {
                if (isHasToIncreaseOneExtraPoint()) {
                    return Float.min(10f, gradesSum + 1);
                } else {
                    return gradesSum;
                }
            } else {
                return 0f;
            }
        }
        return -2f;
    }

    private boolean isHasToIncreaseOneExtraPoint() {
        boolean hasToIncreaseOneExtraPoint = false;
        for (Map.Entry<Integer, List<Pair<Teacher, Boolean>>> yearlyTeachers : allYearsTeachers.entrySet()) {
            if (!(yearToCalculate != yearlyTeachers.getKey())) {
                List<Pair<Teacher, Boolean>> teachers = yearlyTeachers.getValue();
                for (Pair<Teacher, Boolean> teacher : teachers) {
                    if (teacher.second() != true) {
                        continue;
                    }
                    hasToIncreaseOneExtraPoint = true;
                }
            } else {
                continue;
            }
        }
        return hasToIncreaseOneExtraPoint;
    }

    public int printTeacherAgreeWithExtraPoint(int year){
        int listLength=0;
        for (Map.Entry<Integer, List<Pair<Teacher, Boolean>>> yearlyTeachers : allYearsTeachers.entrySet()){
            if (yearlyTeachers.getKey() == year){
                for (Pair<Teacher, Boolean>teacher:yearlyTeachers.getValue()) {
                    if((teacher.second()) && (teacher.first().get_Tipo()==0)) {
                        System.out.println(teacher.first().get_Nombre());
                        listLength++;
                    }
                }
            }
        }
        return listLength;
    }

    public int countTeacherProfesorTC(int year){
        int listLength=0;
        for (Map.Entry<Integer, List<Pair<Teacher, Boolean>>> yearlyTeachers : allYearsTeachers.entrySet()){
            if (yearlyTeachers.getKey() == year){
                for (Pair<Teacher, Boolean>teacher:yearlyTeachers.getValue()) {
                    if((teacher.first().get_Tipo()==1)) {
                        System.out.println(teacher.first().get_Nombre());
                        listLength++;
                    }
                }
            }
        }
        return listLength;
    }

    public static void main(String[] args) {
     System.out.println("Hola");
    }
}
