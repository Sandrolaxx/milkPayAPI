package com.milk.pay.entities.pattern;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

/**
 *
 * @param <Entity>
 */
public interface DafeRepository<Entity extends DafeEntity> extends PanacheRepositoryBase<Entity, Integer> {

}
