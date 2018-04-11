package tryrest.hoges;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import tryrest.entity.Test1;

/**
 * ほげリソース。
 */
@Path("/hoges")
@RequestScoped
public class HogeResource {

	@Inject
	InjectTest yy;

	/**
	 * データストア。
	 */
	private static List<HogeData> store = new ArrayList<HogeData>();

	/**
	 * URI情報。
	 */
	@Context
	private UriInfo uriInfo;

	/**
	 * データ作成。
	 *
	 * @param data データ。
	 * @return レスポンス。
	 */
	@POST
	public Response post(@BeanParam HogeData data) {
		store.add(data);
		URI uri = uriInfo.getAbsolutePathBuilder().path("{id}").build(store.size());
		return Response.created(uri).entity(data).build();
	}

	/**
	 * データリスト取得。
	 *
	 * @return データリスト。
	 */
	@GET
	public List<HogeData> get() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("myUnitInPersistenceXML");
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Test1> cq = cb.createQuery(Test1.class);
		Root<Test1> r = cq.from(Test1.class);
		List<Test1> myEntities = em.createQuery(cq.select(r)).getResultList();
		for (Test1 myEntity : myEntities) {
			System.out.println(myEntity.getId() + ":" + myEntity);
		}
		em.close();

		System.err.println(yy);

		return store;
	}

	/**
	 * データ取得。
	 *
	 * @param id データのID。
	 * @return データ。
	 */
	@GET
	@Path("/{id}")
	public HogeData get(@PathParam("id") int id) {
		return store.get(id - 1);
	}

}
