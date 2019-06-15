package OpenCSV.CSVTest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class CsvController(val service: CsvService) {

    // test data
    companion object {
        val sampleA = NormalModel(1, "Ren", "Akita")
        val sampleB = NormalModel(2, "Shun","Okinawa")
        val sampleC = NormalModel(3, "Mai", "Tokyo")
        val sampleList = listOf(sampleA, sampleB, sampleC)

        val FILE_A = "/Users/kren/kotlin/CSVTest/src/main/resources/csv/sampleA.csv"
        val FILE_B = "/Users/kren/kotlin/CSVTest/src/main/resources/csv/sampleB.csv"
        val FILE_C = "/Users/kren/kotlin/CSVTest/src/main/resources/csv/sampleC.csv"
        val FILE_D = "/Users/kren/kotlin/CSVTest/src/main/resources/csv/sampleD.csv"
    }


    @RequestMapping("/writeDirect")
    fun writeCsvWithDirect(): String{

        val result = service.writeCsvWithDirect(FILE_A, sampleList)

        if(result){
            return "direct write is success!\n"
        }else{
            return "direct write is failed.\n"
        }

    }

    @RequestMapping("/writeBean")
    fun writeCsvWithBean(): String{

        val result = service.writeCsvWithBean(FILE_B, sampleList)

        if(result){
            return "bean write is success!\n"
        }else{
            return "bean write is failed.\n"
        }
    }


    @RequestMapping("/readDirect")
    fun readCsvWithDirect(): List<Array<String>>?{

        var result = service.readCsvWithDirect(FILE_A)

        return result
    }


    @RequestMapping("/readWithOrderedBean_A")
    fun readCsvWithOrderedBeanA(): List<OrderedModel>?{

        var result = service.readCsvWithOrderedBean(FILE_A)

        return result
    }

    @RequestMapping("/readWithOrderedBean_B")
    fun readCsvWithOrderedBeanB(): List<OrderedModel>?{

        var result = service.readCsvWithOrderedBean(FILE_C)

        return result
    }

    @RequestMapping("/readWithNamedBean_A")
    fun readCsvWithNamedBeanA(): List<NamedModel>?{

        var result = service.readCsvWithNamedBean(FILE_D)

        return result
    }

    @RequestMapping("/readWithBean")
    fun readCsvWithBean(): List<NamedModel>?{

        val classInfo = NamedModel::class.java
        var result = service.readCsvWithBean(FILE_D, classInfo)

        return result
    }
}