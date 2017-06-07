package by.goncharov.nc.service;

import by.goncharov.nc.dto.dto.SheetListDto;

/**
 * Created by ivan on 02.06.2017.
 */
public interface ISheetListService extends IService<SheetListDto> {

    SheetListDto getAllSheetByUserId(int id);
    SheetListDto getSheetByCourseIdAndUserId(int courseId, int userId,int score, int shortcomment);


}
