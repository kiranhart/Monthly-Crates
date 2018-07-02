# Monthly Crates
Ever wanted a crate plugin similar to that of the popular factions server CosmicPvP? Well here you go.
## Description
Monthly Crates is a plugin based of the server CosmicPvP, each crate has a total of 10 items. The first 9 items are the normal items, upon clicking one of them row and column animations will start to play while that clicked slot will cycle through all the possible rewards that can be set using the edit menu. Once all 9 main panes are clicked the final animations will start to cycle in (depends on how fast you click through the first slots). But once finished the final reward slot will be unlocked for you to get that reward. These crates are assigned to players by default meaning that if you give a player a crate, only they can open it unless stated otherwise within the config.

## config.yml
```yaml
#I suggest leaving this on to get a more detailed error report :)
debugger: true

cancel-word: "cancel"

#If false, only the person who bought or was given the crate through
#command may open it, otherwise anyone can open it!
allow-nonowner-to-open: true

guis:
  crate:
    title: "&6&l{crate_name}"
    items:
      normal:
        item: "ENDER_CHEST:0"
        name: "&7&l???"
        lore:
          - "&7Click to redeem an item"
          - "&7from this monthly crate"
      final-locked:
        item: "STAINED_GLASS_PANE:14"
        name: "&C&L???"
        lore:
          - "&7You can't open the final reward"
          - "&7until you have redeemed all other rewards."
      fill:
        enabled: true
        item: "STAINED_GLASS_PANE:8"
        name: "&7&l???"
        lore:
          - "&7???"
  listing:
    title: "&EListing all Crates"
    border:
      enabled: true
      item:
        item: "STAINED_GLASS_PANE:4"
        name: "&6~"
        lore:
          - "&e*"
    items:
      currentpage:
        item: "BOOK_AND_QUILL:0"
        name: "&E&LCurrent Page&f: &b{page}"
        lore:
          - "&7--------------------"
      nextpage:
        item: "FEATHER:0"
        name: "&e&l>> Next Page"
        lore:
          - "&7Go to next page if available"
      previouspage:
        item: "FEATHER:0"
        name: "&e&lPrevious Page <<"
        lore:
          - "&7Go to previous page if available"
  edit:
    title: "&E&LEditing &6{crate_name} &eCrate"
    items:
      normalpane:
        item: "STAINED_GLASS_PANE:5"
        name: "&b&lEdit Slot&F: &7#&e{normal_pane_number}"
        lore:
          - "&7Click this pane to begin editing the items"
          - "&7that can be earned in the roulette this pane."
      finalpane:
        item: "STAINED_GLASS_PANE:5"
        name: "&b&lEdit Final Reward Pane"
        lore:
          - "&7Click this pane to begin editing the items"
          - "&7that can be earned in final pane roulette."
      name:
        item: "STAINED_GLASS_PANE:2"
        name: "&b&lEdit Crate Title"
        lore:
          - "&7Click to change the editing crate's name."
      item:
        item: "STAINED_GLASS_PANE:2"
        name: "&b&lEdit Crate Item"
        lore:
          - "&7Click to change the physical item that will"
          - "&7be given as a crate for this crate."
      animationtheme:
        item: "STAINED_GLASS_PANE:2"
        name: "&b&lEdit Crate Theme"
        lore:
          - "&7Click to change the animation theme of this"
          - "&7crate (Color scheme during the animations)"
```

### API
```java
//Get the crate api instance
CrateAPI.getInstance()
//Here's a one useful method from that class
//Returns a list of crates as itemstacks
CrateAPI.getInstance().getListOfCrates() 
```
