package OpenCSV.CSVTest

import com.opencsv.bean.CsvBindByName

class NamedModel (

    @CsvBindByName(column = "番号")
    var No: Int? = null,

    @CsvBindByName(column = "名前")
    var Name: String? = null,

    @CsvBindByName(column = "場所")
    var Place: String? = null
){}