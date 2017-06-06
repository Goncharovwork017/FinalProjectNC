package by.goncharov.nc.dao;

import by.goncharov.nc.entities.SheetList;

/**
 * Created by ivan on 02.06.2017.
 */
public interface ISheetListDao extends IDao<SheetList> {

    SheetList getAllSheetByUserId(int id);

    SheetList getSheetByCourseIdAndUserId(int courseId, int userId);


}