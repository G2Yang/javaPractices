/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.proven.view;

import cat.proven.menu.Menu;
import cat.proven.menu.Option;

/**
 *
 * @author dax
 */
public class MainMenu extends Menu {

    public MainMenu() {
        this.title = "Store main menu";
        this.addOption(new Option("Exit", "exit"));
        this.addOption(new Option("List all categories", "category/all"));
        this.addOption(new Option("Search categories by code", "category/code"));
        this.addOption(new Option("Add a new category", "category/add"));
        this.addOption(new Option("Modify category", "category/modify"));
        this.addOption(new Option("Remove category", "category/remove"));

        this.addOption(new Option("List all products", "product/all"));
        this.addOption(new Option("Search products by code", "product/code"));
        this.addOption(new Option("Search products like name", "product/name"));
        this.addOption(new Option("Add a new product", "product/add"));
        this.addOption(new Option("Modify product", "product/modify"));
        this.addOption(new Option("Remove product", "product/remove"));
        this.addOption(new Option("Search products by category", "product/category"));
    }
}
