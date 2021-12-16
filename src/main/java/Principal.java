import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class Principal {
    final private Map<Integer, List<Pair<Teacher, Boolean>>> allYearsTeachers = Map.ofEntries(
            new AbstractMap.SimpleImmutableEntry<>(
                    2020,
                    List.of(
                            new Pair<>( new Teacher(1,"Josefina"), true),
                            new Pair<>( new Teacher(1,"Edonisio"), true),
                            new Pair<>( new Teacher(1,"Edufasio"), true)
                    )
            ),
            new AbstractMap.SimpleImmutableEntry<>(
                    2019,
                    List.of(
                            new Pair<>( new Teacher(1,"Eduarda"), false),
                            new Pair<>( new Teacher(1,"Abelardo"), true),
                            new Pair<>( new Teacher(1,"Francisca"), false)
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
            gradesSum += (examGrade.first() * examGrade.second() / 100);
        }
        return gradesSum;
    }


    private float gradeVerification(boolean hasReachedMinimumClasses, float gradesSum, int gradesWeightSum) {
        if (gradesWeightSum == 100) {
            if (hasReachedMinimumClasses) {
                if (isHasToIncreaseOneExtraPoint()) {
                    return Float.min(10f, gradesSum + 1);
                } else {
                    return gradesSum;
                }
            } else {
                return 0f;
            }
        } else if (gradesWeightSum > 100) {
            return -1f;
        } else {
            return -2f;
        }
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

    public void imprimirListado(){
        for (Map.Entry<Integer, List<Pair<Teacher, Boolean>>> yearlyTeachers : allYearsTeachers.entrySet()){
            for (Pair<Teacher, Boolean>teacher:yearlyTeachers.getValue()) {
                if(teacher.second()) System.out.println(teacher.first().get_Nombre());
            }
        }
    }

    public static void main(String[] args) {
     System.out.println("Hola");
    }
}
