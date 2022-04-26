## Maven

[![](https://jitpack.io/v/ScropyTR/LegendInventoryAPI.svg)](https://jitpack.io/#ScropyTR/LegendInventoryAPI)

```pom.xml

<dependency>
    <groupId>com.github.ScropyTR</groupId>
    <artifactId>LegendInventoryAPI</artifactId>
    <version>1.0.0</version>
</dependency>

<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```


## Setup

``` java
InventoryAPI.setup(this);
```
Add to onEnable method of your plugin


## Usage

### Simple GUI

``` java
public class ExampleGUI extends GUI {

    @Override
    public String getTitle() {
        return "§2§lYes §8or §c§lNo";
    }

    @Override
    public int getSize() {
        return 9;
    }
    
    @Override
    public void addContent() {

        Item closeItem = new ItemBuilder(XMaterial.BARRIER).setDisplayName("§c§lClose")
                .glowing().build()
                .withListener(clickEvent -> clickEvent.getWhoClicked().closeInventory());

        Item noItem = new ItemBuilder(XMaterial.RED_WOOL).setDisplayName("§c§lNo").build()
                .withListener(clickEvent -> clickEvent.getWhoClicked().sendMessage("§c§lNo"));

        Item yesItem = new ItemBuilder(XMaterial.LIME_WOOL).setDisplayName("§a§lYes").build()
                .withListener(clickEvent -> clickEvent.getWhoClicked().sendMessage("§a§lYes"));

        setItem(closeItem, 4);
        setItem(yesItem, 2);
        setItem(noItem, 6);

    }


    @Override
    public void onOpen(InventoryOpenEvent event) {

    }

    @Override
    public void onClose(InventoryCloseEvent event) {

    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
```

![gui](C:\Users\Birol\Desktop\GUI.gif)


### Paginated GUI

``` java

public class ExampleGUI extends PaginatedGUI<User> {

    @Override
    public String getTitle() {
        return "§8§nChoose a player";
    }

    @Override
    public int getSize() {
        return 54;
    }
    
    @Override
    public void addContent() {
        List<Integer> slots = Arrays.asList(0,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 51, 52, 53);

        setItem(new ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE).setDisplayName("").build(), slots);

        Item closeItem = new ItemBuilder(XMaterial.BARRIER).setDisplayName("§c§lClose")
                .glowing().build()
                .withListener(clickEvent -> clickEvent.getWhoClicked().closeInventory());

        Item nextPage = new ItemBuilder(XMaterial.ARROW).setDisplayName("§a§lNext").build()
                .withListener(clickEvent -> nextPage());

        Item previousPage = new ItemBuilder(XMaterial.ARROW).setDisplayName("§e§lPrevious").build()
                .withListener(clickEvent -> previousPage());

        setItem(closeItem, 49);
        setItem(previousPage, 48);
        setItem(nextPage, 50);

        super.addContent();
    }


    @Override
    public List<User> getPaginatedObjects() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i<60; ++i) {
            users.add(new User("Scropy"));
        }
        return users;
    }

    @Override
    public Item getItem(User user) {
        return new ItemBuilder(XMaterial.PLAYER_HEAD).setDisplayName("§b" + user.getName())
                .setLore(Arrays.asList("", " §e► Click to select player"))
                .setHeadData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWVjY2RhNzBiZWFkOWY2N2IzOWRjZThiMDQwYWQwZjA4ZWZjMjMwNWMxZjY4NDYxMTY0N2EwMThhNjY0NTJjMiJ9fX0=")
                .build();
    }

    @Override
    public List<Integer> getSlots() {
        return Arrays.asList(
                10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43
        );
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {

    }

    @Override
    public void onClose(InventoryCloseEvent event) {

    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }

}

```
![paginated gui](https://user-images.githubusercontent.com/70208466/165129056-e0fc30fe-cbc6-4947-943b-755ca4249e7d.gif)

``` java
    ExampleGUI exampleGUI = new ExampleGUI();

    exampleGUI.open(player);
```
