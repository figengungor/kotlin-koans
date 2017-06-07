package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (year != other.year) return year - other.year
        else if (month != other.month) return month - other.month
        else return dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator  fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval):MyDate{
    return addTimeIntervals(repeatedTimeInterval.timeInterval, repeatedTimeInterval.number)
}

operator  fun MyDate.plus(timeInterval: TimeInterval):MyDate{
    return addTimeIntervals(timeInterval,1)
}

enum class TimeInterval() {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(count:Int):RepeatedTimeInterval = RepeatedTimeInterval(this, count)

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var cursor = start
            override fun hasNext(): Boolean {
                return cursor <= endInclusive
            }

            override fun next(): MyDate {
                var current = cursor
                cursor = cursor.nextDay()
                return current
            }
        }
    }

    operator fun contains(date: MyDate): Boolean {
        return date > start && date <= endInclusive
    }

    /*inner class MyDateIterator : Iterator<MyDate> {
        var cursor = start

        override fun next(): MyDate {
            var current = cursor
            cursor = cursor.nextDay()
            return current
        }

        override fun hasNext(): Boolean {
            return cursor<=endInclusive
        }

    }*/
}


