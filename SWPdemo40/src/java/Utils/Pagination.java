/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import DTO.Product;
import java.util.List;

/**
 *
 * @author kienb
 */
public class Pagination {

    public static List<Product> Paging(List<Product> list, int pageNumber, int size) {
        if(list.isEmpty()){
            return list;
        }
        return list.subList((pageNumber - 1) * size, pageNumber * size >= list.size() ? list.size() : pageNumber * size);
    }
}
