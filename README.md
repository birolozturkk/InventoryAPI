## Maven

[![](https://jitpack.io/v/ScropyTR/LegendInventoryAPI.svg)](https://jitpack.io/#ScropyTR/LegendInventoryAPI)

```pom.xml

<dependency>
    <groupId>com.github.ScropyTR</groupId>
    <artifactId>LegendInventoryAPI</artifactId>
    <version>1.1.9</version>
</dependency>

<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```


## Setup

```java
InventoryAPI.setup(this);
```
Add to onEnable method of your plugin


## Usage

``` java
public class ExampleGUI extends GUI {

    public ExampleGUI(InventoryAPI inventoryAPI, String title, int size) {
        super(inventoryAPI, title, size);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        super.onOpen(event);

        event.getPlayer().sendMessage("Inventory opened");

    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        super.onClose(event);

        event.getPlayer().sendMessage("Inventory closed");
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        super.onClick(event);

        event.setCancelled(true);
    }
}
```


### Simple GUI

``` java
    ExampleGUI exampleGUI = new ExampleGUI(inventoryAPI, "exampleGUI", 54);

    Item item = new ItemBuilder(Material.SKULL_ITEM).setDisplayName("display name").setLore(Arrays.asList("s", "s1", "s2"))
            .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzYxZDY1N2M2ZWUwODNiZGE1NjQxMzQ1N2Y3YzEwY2JhZmVkNjdlMmJkNmFjOWYzZjRlNDg1Yzk0Zjg5Yjg3MSJ9fX0=")
            .setAction(clickEvent -> {
                clickEvent.setCancelled(true);
            })
            .build();

    exampleGUI.setItem(item, 2);
    exampleGUI.addItem(item);

    exampleGUI.open(player);

```


### Paginated GUI

``` java
    ExampleGUI exampleGUI = new ExampleGUI(inventoryAPI, "exampleGUI", 54);
    Pagination pagination = exampleGUI.getPagination();

    List<Item> items = new ArrayList<>();
    for (int i = 0; i <= 99; i++) {
        Item item = new ItemBuilder(Material.SKULL_ITEM).setDisplayName("display name").setLore(Arrays.asList("s", "s1", "s2")).setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzYxZDY1N2M2ZWUwODNiZGE1NjQxMzQ1N2Y3YzEwY2JhZmVkNjdlMmJkNmFjOWYzZjRlNDg1Yzk0Zjg5Yjg3MSJ9fX0=").build();
        items.add(item);
    }

    Pagination pagination = exampleGUI.getPagination();
    pagination.setSlots(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19));

    pagination.setItems(items);

    Item nextPageButton = new ItemBuilder(Material.SKULL_ITEM).setDisplayName("next")
            .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE5MmFkNDU2Zjc2ZWM3Y2FhMzU5NTkyMmQ1ZmNjMzhkY2E5MjhkYzY3MTVmNzUyZTc0YzhkZGRlMzQ0ZSJ9fX0=")
            .setAction(clickEvent -> {
                pagination.nextPage(player);
            }).build();

    Item previousPageButton = new ItemBuilder(Material.SKULL_ITEM).setDisplayName("previous")
            .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZhNjA1ZTI1ZjRmYzJjZWE1YTc2NmQ3OWE4YmZhMjkwMzEzZTQ1ZDhmNWU5NTdkOTU4YTBmMzNmY2IxNiJ9fX0=")
            .setAction(clickEvent -> {
                pagination.previousPage(player);
            })
            .build();


    exampleGUI.setItem(nextPageButton, 23);
    exampleGUI.setItem(previousPageButton, 22);

    exampleGUI.open(player);

```

