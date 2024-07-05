
package ndt;

import java.util.List;

import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateMinMaxGen {
    
    @UserFunction
    @Description("ndt.min_date(dateA, dateB) - returns the minimum of two date objects, or the first non-null argument.")
    public java.time.LocalDate min_date(
        @Name("dateA") java.time.LocalDate dateA,
        @Name("dateB") java.time.LocalDate dateB
    ) {
        if (dateA == null) {
            return (dateB == null) ? null : dateB;
        }
        if (dateB == null) {
            return dateA;
        }
        return dateA.compareTo(dateB) <= 0 ? dateA : dateB;
    }

    @UserFunction
    @Description("ndt.max_date(dateA, dateB) - returns the maximum of two date objects, or the first non-null argument.")
    public java.time.LocalDate max_date(
        @Name("dateA") java.time.LocalDate dateA,
        @Name("dateB") java.time.LocalDate dateB
    ) {
        if (dateA == null) {
            return (dateB == null) ? null : dateB;
        }
        if (dateB == null) {
            return dateA;
        }
        return dateA.compareTo(dateB) >= 0 ? dateA : dateB;
    }

    @UserFunction
    @Description("ndt.min_localdatetime(dateA, dateB) - returns the minimum of two localdatetime objects, or the first non-null argument.")
    public java.time.LocalDateTime min_localdatetime(
        @Name("dateA") java.time.LocalDateTime dateA,
        @Name("dateB") java.time.LocalDateTime dateB
    ) {
        if (dateA == null) {
            return (dateB == null) ? null : dateB;
        }
        if (dateB == null) {
            return dateA;
        }
        return dateA.compareTo(dateB) <= 0 ? dateA : dateB;
    }

    @UserFunction
    @Description("ndt.max_localdatetime(dateA, dateB) - returns the maximum of two localdatetime objects, or the first non-null argument.")
    public java.time.LocalDateTime max_localdatetime(
        @Name("dateA") java.time.LocalDateTime dateA,
        @Name("dateB") java.time.LocalDateTime dateB
    ) {
        if (dateA == null) {
            return (dateB == null) ? null : dateB;
        }
        if (dateB == null) {
            return dateA;
        }
        return dateA.compareTo(dateB) >= 0 ? dateA : dateB;
    }

    @UserFunction
    @Description("ndt.min_datetime(dateA, dateB) - returns the minimum of two datetime objects, or the first non-null argument.")
    public java.time.ZonedDateTime min_datetime(
        @Name("dateA") java.time.ZonedDateTime dateA,
        @Name("dateB") java.time.ZonedDateTime dateB
    ) {
        if (dateA == null) {
            return (dateB == null) ? null : dateB;
        }
        if (dateB == null) {
            return dateA;
        }
        return dateA.compareTo(dateB) <= 0 ? dateA : dateB;
    }

    @UserFunction
    @Description("ndt.max_datetime(dateA, dateB) - returns the maximum of two datetime objects, or the first non-null argument.")
    public java.time.ZonedDateTime max_datetime(
        @Name("dateA") java.time.ZonedDateTime dateA,
        @Name("dateB") java.time.ZonedDateTime dateB
    ) {
        if (dateA == null) {
            return (dateB == null) ? null : dateB;
        }
        if (dateB == null) {
            return dateA;
        }
        return dateA.compareTo(dateB) >= 0 ? dateA : dateB;
    }
}
