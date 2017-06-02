package by.goncharov.nc.controllers;

import by.goncharov.nc.dto.dto.SheetListDTO;
import by.goncharov.nc.services.inter.ISheetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ivan on 02.06.2017.
 */


@RestController
@RequestMapping(value = "/sheet")
public class SheetListController {



    @Autowired
    private ISheetListService sheetListService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<SheetListDTO>> getAllSheet() {
        List<SheetListDTO> list = sheetListService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SheetListDTO> getSheetById(@PathVariable("id") Integer id) {
        SheetListDTO sheet = sheetListService.getById(id);
        return new ResponseEntity<>(sheet, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") Integer id) {
        SheetListDTO sheet = sheetListService.getById(id);
        if (sheet != null) {
            sheetListService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }




    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createCourse(@RequestBody SheetListDTO sheetListDTO) {
        sheetListService.save(sheetListDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }



    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity updateCategory(@RequestBody SheetListDTO sheetListDTO) {
        sheetListService.update(sheetListDTO);
        sheetListDTO = sheetListService.getById(sheetListDTO.getId());
        return new ResponseEntity<>(sheetListDTO, HttpStatus.OK);
    }




}
