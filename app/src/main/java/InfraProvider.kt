import android.content.Context
import com.merge.adapter.sample.DetailRepositoryImpl
import com.merge.adapter.sample.ListRepositoryImpl

object InfraProvider {
    private lateinit var applicationContext: Context

    fun init(context: Context) {
        this.applicationContext = context
    }

    private val listTemplateRepository: ListRepositoryImpl by lazy {
        ListRepositoryImpl(applicationContext)
    }

    private val detailItemRepository: DetailRepositoryImpl by lazy {
        DetailRepositoryImpl(applicationContext)
    }

    fun getListRepository() = listTemplateRepository
    fun getDetailRepository() = detailItemRepository
}