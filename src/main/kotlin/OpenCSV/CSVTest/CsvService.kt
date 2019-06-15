package OpenCSV.CSVTest

interface CsvService {

    fun writeCsvWithDirect(filename: String, data: List<NormalModel>): Boolean

    fun writeCsvWithBean(filename: String, data: List<NormalModel>): Boolean

    fun readCsvWithDirect(filename: String): List<Array<String>>?

    fun readCsvWithOrderedBean(filename: String): List<OrderedModel>?

    fun readCsvWithNamedBean(filename: String): List<NamedModel>?

    fun <T> readCsvWithBean(filename: String, classInfo: Class<T>): List<T>?
}

