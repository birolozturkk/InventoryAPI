[![](https://jitpack.io/v/ScropyTR/LegendInventoryAPI.svg)](https://jitpack.io/#ScropyTR/LegendInventoryAPI)

## Maven

```pom.xml

<dependency>
    <groupId>com.github.ScropyTR</groupId>
    <artifactId>LegendInventoryAPI</artifactId>
    <version>0.0.6</version>
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

![GUI](https://user-images.githubusercontent.com/70208466/165330915-905ec7ac-daaa-41fd-bdd1-6be51883e475.gif)


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

        setItem(new ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE).setDisplayName(" ").build(), slots);

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
    public List<Player> getPaginatedObjects() {
        return new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
    }

    @Override
    public Item getItem(Player player) {
        return new ItemBuilder(XMaterial.PLAYER_HEAD).setDisplayName("§b" + player.getName())
                .setLore(Arrays.asList("", " §e► Click to select player"))
                .setHeadData(SkinUtils.getHeadData(player.getUniqueId()))
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
![PaginatedGUI](https://user-images.githubusercontent.com/70208466/165331206-f97ab530-d39b-4e3c-afc7-2cffc444c17b.gif)

``` java
    ExampleGUI exampleGUI = new ExampleGUI();

    exampleGUI.open(player);
```
