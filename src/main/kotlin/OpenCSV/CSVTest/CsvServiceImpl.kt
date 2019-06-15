package OpenCSV.CSVTest

import com.opencsv.CSVReader
import org.springframework.stereotype.Service
import com.opencsv.CSVWriter
import com.opencsv.bean.CsvToBean
import com.opencsv.bean.CsvToBeanBuilder
import com.opencsv.bean.StatefulBeanToCsv
import com.opencsv.bean.StatefulBeanToCsvBuilder
import java.io.BufferedReader
import java.io.FileReader
import java.io.FileWriter

@Service
class CsvServiceImpl: CsvService {



    override fun writeCsvWithDirect(filename: String, data: List<NormalModel>): Boolean{

        var fileWriter = FileWriter(filename)
        var csvWriter: CSVWriter?  = null

        fileWriter.use{

            try {

                csvWriter = CSVWriter(it)

                data.forEach {
                    val target = arrayOf(it.No.toString(), it.Name!!, it.Place!!)
                    csvWriter!!.writeNext(target)
                }

            }catch(e: Exception){
                return false
            }finally{
                csvWriter!!.close()
            }
        }

        return true
    }


    override fun writeCsvWithBean(filename: String, data: List<NormalModel>): Boolean{

        val fileWriter = FileWriter(filename)
        var beanToCsv: StatefulBeanToCsv<NormalModel>? = null

        fileWriter.use {

            try{

                beanToCsv = StatefulBeanToCsvBuilder<NormalModel>(it)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build()

                beanToCsv!!.write(data)

            }catch(e: Exception){
                return false
            }
        }

        return true
    }


    override fun readCsvWithDirect(filename: String): List<Array<String>>?{

        val fileReader = BufferedReader(FileReader(filename))
        var csvReader: CSVReader? = null
        var records: List<Array<String>>? = null

        fileReader.use{

            try {
                csvReader = CSVReader(it)

                records = csvReader!!.readAll()

            }catch(e: Exception){
                return records
            }finally {
                csvReader!!.close()
            }
        }

        return records
    }


    override fun readCsvWithOrderedBean(filename: String): List<OrderedModel>?{

        val fileReader = BufferedReader(FileReader(filename))
        var csvToBean: CsvToBean<OrderedModel>? = null
        var records: List<OrderedModel>? = null

        fileReader.use{

            try {
                csvToBean = CsvToBeanBuilder<OrderedModel>(fileReader)
                        .withType(OrderedModel::class.java).build()
                records = csvToBean!!.parse()

            }catch(e: Exception){
                return records
            }
        }

        return records
    }


    override fun readCsvWithNamedBean(filename: String): List<NamedModel>?{

        val fileReader = BufferedReader(FileReader(filename))
        var csvToBean: CsvToBean<NamedModel>? = null
        var records: List<NamedModel>? = null

        fileReader.use{

            try {
                csvToBean = CsvToBeanBuilder<NamedModel>(fileReader)
                        .withType(NamedModel::class.java).build()
                records = csvToBean!!.parse()

            }catch(e: Exception){
                return records
            }
        }

        return records
    }


    override fun <T> readCsvWithBean(filename: String, classInfo: Class<T>): List<T>?{

        val fileReader = BufferedReader(FileReader(filename))
        var csvToBean: CsvToBean<T>? = null
        var records: List<T>? = null

        fileReader.use{

            try {
                csvToBean = CsvToBeanBuilder<T>(fileReader)
                        .withType(classInfo).build()
                records = csvToBean!!.parse()

            }catch(e: Exception){
                return records
            }
        }

        return records
    }
}