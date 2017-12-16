/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.messenger.model;

import java.io.Serializable;
import ru.sfedu.messenger.model.dto.EntityType;

/**
 *
 * @author Vovka
 */
public abstract interface IDataProvider<T> {
    public Result<T> saveRecord(T bean, EntityType type);
    public Result<T> deleteRecord(T bean, EntityType type);
    public Result<T> getRecordById(long id, EntityType type);
    public Result updateRecord(T bean, EntityType type); 
    public Result getAllRecords(EntityType type);
    public Result initDataSource();
}
