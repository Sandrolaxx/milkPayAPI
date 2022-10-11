package com.aktie.aktiepay.entities.pattern;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

/**
 * @author SRamos
 * @param <Entity>
 */
public interface DafeRepository<Entity extends DafeEntity> extends PanacheRepositoryBase<Entity, Integer> {
}
