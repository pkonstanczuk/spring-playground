package pl.qkon.grocery

import org.springframework.stereotype.Component
import pl.qkon.grocery.generated.rest.apis.StoreApiService
import pl.qkon.grocery.generated.rest.models.Order
import java.util.concurrent.ConcurrentHashMap

@Component
class StoreService : StoreApiService {
  private val storage: ConcurrentHashMap<String, Order> = ConcurrentHashMap<String, Order>()

  override fun listOrders(): List<Order> {
    return storage.values.toList()
  }
}
