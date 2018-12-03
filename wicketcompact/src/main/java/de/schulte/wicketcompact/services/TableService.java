package de.schulte.wicketcompact.services;

import de.schulte.wicketcompact.entities.Table;

import java.util.List;
import java.util.stream.Collectors;

public class TableService extends BaseService<Table> {

    public TableService() {
        final Table costarica = new Table("Costa Rica", 4);
        final Table panama = new Table("Panama", 4);
        final Table honduras = new Table("Honduras", 6);
        final Table mexiko = new Table("Mexiko", 12);
        final Table kuba = new Table("Kuba", 8);

        save(costarica);
        save(panama);
        save(honduras);
        save(mexiko);
        save(kuba);
    }

    public Iterable<String> listTableNamesContaining(String nameFragment) {
        return listAll().stream().filter(table -> table.getName().contains(nameFragment)).map(table -> table.getName()).collect(Collectors.toList());
    }

    public Table getByName(String name) {
        final List<Table> filtered = listAll().stream().filter(table -> table.getName().equals(name)).collect(Collectors.toList());
        return filtered.isEmpty() ? null : filtered.get(0);
    }
}
