![Could not load image](https://i.imgur.com/SqXRFWr.png "The Logo")
Ever wanted a crate plugin similar to that of the popular factions server CosmicPvP? Well here you go.
## Description
Monthly Crates is a plugin based of the server CosmicPvP, each crate has a total of 10 items. The first 9 items are the normal items, upon clicking one of them row and column animations will start to play while that clicked slot will cycle through all the possible rewards that can be set using the edit menu. Once all 9 main panes are clicked the final animations will start to cycle in (depends on how fast you click through the first slots). But once finished the final reward slot will be unlocked for you to get that reward. These crates are assigned to players by default meaning that if you give a player a crate, only they can open it unless stated otherwise within the config.

## Permissions
```yaml
MonthlyCrates
MonthlyCrates.cmd.help
MonthlyCrates.cmd.create
MonthlyCrates.cmd.remove
MonthlyCrates.cmd.edit
MonthlyCrates.cmd.give
MonthlyCrates.cmd.giveall
MonthlyCrates.cmd.list
```
## Commands 
  - /MonthlyCrates create <crate name>
  - /MonthlyCrates remove <crate name>
  - /MonthlyCrates edit <crate name>
  - /MonthlyCrates give <crate> <player> <#>
  - /MonthlyCrates giveall <crate> <#>

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
## en_US.lang
You can always add other valid translated lang files. OR download premade ones if available on the github.
```lang
prefix = "&8[&eMCrates&8]"
help = "&cPlease enter a subcommand (/monthlycrates help)"

#Commands
commands.create = "&e&lUsage&f: &7/MCrate create &c<name>"
commands.remove = "&e&lUsage&f: &7/MCrate remove &c<name>"
commands.giveall = "&e&lUsage&f: &7/MCrate giveall &c<crate> <#>"
commands.give= "&e&lUsage&f: &7/MCrate give &c<crate> <player> <#>"

#Misc
nopermission = "&cYou do not have permission to use that command!"
playersonly = "&cOnly players are allowed to use that command!"
invalidsubcommand = "&cThat's an invalid subcommand!"
playeroffline = "&cThat player is currently offline!"
disabled = "&cFeatured disabled by resource author!"
canceledit = "&CYou're no longer editing that crate!"
notanumber = "&cThat isn't a whole number!"
listing.found = "&eListing &b{amount} &ecrates from data file."
listing.none = "&cThere are no monthly crates setup right now!"

#Crate
crate.created = "&eYou successfully created a new crate named&f: &6{crate_name}"
crate.removed = "&eYou successfully removed the crate named&f: &6{crate_name}"
crate.give = "&eYou gave &6{player} &f{amount} &b{crate_name} crate(s)."
crate.giveall = "&eYou gave everyone &f{amount} &b{crate_name} crate(s)."
crate.received = "&EYou received a crate from &b{player}"
crate.saved = "&eSuccessfully saved that edit to the crate."
crate.exist = "&cA crate with that id/name already exist!"
crate.missing = "&cCould not find any crate with that name/id!"
crate.edit.title = "&EPlease type the new name in chat type 'cance' to cancel."
crate.cantexit = "&CYou must finish opening your crate to close the window."
```

### API
```java
//Get the crate api instance
CrateAPI.getInstance()
//Here's a one useful method from that class
//Returns a list of crates as itemstacks
CrateAPI.getInstance().getListOfCrates() 
```
