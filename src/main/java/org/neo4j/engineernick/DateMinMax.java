package engineernick;

import java.util.List;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateMinMax {

    @UserFunction
    @Description("engineernick.datemax(dateA, dateB) - returns later of two inputs, or the first non-null input, or null if both are null.")
    public LocalDate datemax(
        @Name("dateA") LocalDate dateA,
        @Name("dateB") LocalDate dateB
    ) {
        if (dateA == null) {
            return (dateB == null) ? null : dateB;
        }
        if (dateB == null) {
            return dateA;
        }
        return dateA.isAfter(dateB) ? dateA : dateB;
    }

    @UserFunction
    @Description("engineernick.datemin(dateA, dateB) - returns earlier of two inputs, or the first non-null input, or null if both are null.")
    public LocalDate datemin(
        @Name("dateA") LocalDate dateA,
        @Name("dateB") LocalDate dateB
    ) {
        if (dateA == null) {
            return (dateB == null) ? null : dateB;
        }
        if (dateB == null) {
            return dateA;
        }
        return dateA.isBefore(dateB) ? dateA : dateB;
    }

}