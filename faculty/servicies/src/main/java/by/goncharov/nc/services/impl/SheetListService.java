package by.goncharov.nc.services.impl;

import by.goncharov.nc.constants.ServiceConstants;
import by.goncharov.nc.dao.inter.ISheetListDAO;
import by.goncharov.nc.dto.converters.Converter;
import by.goncharov.nc.dto.dto.SheetListDTO;
import by.goncharov.nc.entities.SheetList;
import by.goncharov.nc.services.inter.ISheetListService;
import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 01.06.2017.
 */

@Service
@Transactional
public class SheetListService implements ISheetListService {



    @Autowired
    private ISheetListDAO sheetListDAO;
    private final static Logger logger = Logger.getLogger(SheetListService.class);

    @Autowired
    public SheetListService(ISheetListDAO sheetListDAO) {
        this.sheetListDAO = sheetListDAO;
    }



    @Override
    public int save(SheetListDTO sheetListDTO) {
        SheetList sheetList = Converter.sheetDtoToSheet(sheetListDTO);
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
    public List<SheetListDTO> getAll() {
        List<SheetList> sheetLists = null;
        SheetListDTO sheetListDTO = null;
        List<SheetListDTO> sheetListDTOS = new ArrayList<SheetListDTO>();
        if (logger.isDebugEnabled()) {
            logger.debug(ServiceConstants.TRANSACTION_DEBUG);
        }
        try {
            sheetLists = sheetListDAO.getAll();
            for (SheetList sheet : sheetLists) {
                sheetListDTO = Converter.sheetToSheetDto(sheet);
                sheetListDTOS.add(sheetListDTO);
            }
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return sheetListDTOS;
    }

    @Override
    public SheetListDTO getById(int id) {
        SheetList sheetList = null;
        SheetListDTO sheetListDTO = null;
        try {
            sheetList = sheetListDAO.getById(id);
            if (sheetList != null)
                sheetListDTO = Converter.sheetToSheetDto(sheetList);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return sheetListDTO;
    }

    @Override
    public void update(SheetListDTO sheetListDTO) {

        SheetList sheetList = Converter.sheetDtoToSheet(sheetListDTO);

        try {
            sheetListDAO.update(sheetList);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            sheetListDAO.delete(id);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
    }
}
