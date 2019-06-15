package OpenCSV.CSVTest

import com.opencsv.bean.CsvBindByPosition

data class OrderedModel (

    @CsvBindByPosition(position = 0)
    var No: Int? = null,

    @CsvBindByPosition(position = 1)
    var Name: String? = null,

    @CsvBindByPosition(position = 2)
    var Place: String? = null
){}