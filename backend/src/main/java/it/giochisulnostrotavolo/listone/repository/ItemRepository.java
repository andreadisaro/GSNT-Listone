package it.giochisulnostrotavolo.listone.repository;

import it.giochisulnostrotavolo.listone.entities.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface ItemRepository
    extends JpaRepository<Item, Long> // , JpaSpecificationExecutor<Item>
{
  public @NonNull List<Item> findAll();
  /*
   * @Query(
   * value =
   * "select Items.*, count(orders.id_customer) from Items inner join orders on Items.Itemname = orders.id_customer where Items.Itemname like %:Itemname% group by Items.Itemname order by count(orders.id_customer) desc"
   * ,
   * nativeQuery = true
   * )
   * List<Map<String, Object>> ItemAnalytics(@Param("Itemname") String Itemname);
   */
}
