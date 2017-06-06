package by.goncharov.nc.service.impl;

import by.goncharov.nc.constants.ServiceConstants;
import by.goncharov.nc.dao.ICourseDao;
import by.goncharov.nc.dao.ISheetListDao;
import by.goncharov.nc.dto.converters.Converter;
import by.goncharov.nc.dto.dto.SheetListDto;
import by.goncharov.nc.entities.SheetList;
import by.goncharov.nc.exceptions.NotFoundException;
import by.goncharov.nc.service.ISheetListService;
import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 02.06.2017.
 */
@Service
@Transactional
public class SheetListServiceImpl implements ISheetListService {
    private static Logger logger = Logger.getLogger(SheetListServiceImpl.class);


    @Autowired
    private ISheetListDao sheetListDAO;

    @Autowired
    private ICourseDao courseDAO;

    @Autowired
    public SheetListServiceImpl(ISheetListDao sheetListDAO) {

        this.sheetListDAO = sheetListDAO;
    }

    @Override
    public List<SheetListDto> getAll() {
        List<SheetList> sheetLists = null;
        SheetListDto sheetListDto = null;
        List<SheetListDto> sheetListDtos = new ArrayList<SheetListDto>();
        if (logger.isDebugEnabled()) {
            logger.debug(ServiceConstants.TRANSACTION_DEBUG);
        }
        try {
            sheetLists = sheetListDAO.getAll();
            for (SheetList sheet : sheetLists) {
                sheetListDto = Converter.sheetToSheetDto(sheet);
                sheetListDtos.add(sheetListDto);
            }
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return sheetListDtos;
    }

    @Override
    public int save(SheetListDto sheetListDto) {
        SheetList sheetList = Converter.sheetDtoToSheet(sheetListDto);
        int id = 0;
        try {
            id = sheetListDAO.save(sheetList);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return id;
    }

    @Override
    public SheetListDto getById(int id) {
        SheetList sheetList = null;
        SheetListDto sheetListDto = null;
        try {

            sheetList = sheetListDAO.getById(id);

            if (sheetList == null){
                throw new NotFoundException("SheetList not found!");
            }
                sheetListDto = Converter.sheetToSheetDto(sheetList);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return sheetListDto;
    }

    @Override
    public void update(SheetListDto sheetListDto) {
        SheetList sheetList = Converter.sheetDtoToSheet(sheetListDto);
        try {

                SheetList actulSheet = sheetListDAO.getById(sheetList.getId());
                if (actulSheet == null) {
                    throw new NotFoundException("SheetList not found!");
                }
                sheetListDAO.update(sheetList);
                logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
            } catch (ServiceException e) {
                logger.error(ServiceConstants.TRANSACTION_FAILED, e);
            }
        }

    @Override
    public void delete(int id) {
        try {
            SheetList sheetList = sheetListDAO.getById(id);
            if(sheetList == null){
                throw new NotFoundException("SheetList not found!");
            }
            sheetListDAO.delete(id);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
    }

    @Override
    public SheetListDto getAllSheetByUserId(int id) {
        SheetList sheet = null;
        SheetListDto sheetDto = null;
        try{
            sheet = sheetListDAO.getById(id);
            //if(sheet != null)
                if(sheet == null){
                    throw new NotFoundException("Please, add to course!");
                }
                sheetDto = Converter.sheetToSheetDto(sheet);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }

    return  sheetDto;
    }

    @Override
    public SheetListDto getSheetByCourseIdAndUserId(int courseId, int userId) {
        SheetList sheet = null;
        SheetListDto sheetListDto = null;
        try{

            sheet = sheetListDAO.getSheetByCourseIdAndUserId(courseId,userId);
            if(sheet != null) {
                sheetListDto = Converter.sheetToSheetDto(sheet);
                logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
            }
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return  sheetListDto;
    }




}
