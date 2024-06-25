Feature: Popups
  @Regression
  Scenario: AG001 Verify proxy checks
    Given Launch the settings application
    And Tap On wi-fi button
    Then Verify wi-fi screen
    And Tap on info icon of the wifi
    When Proxy is in off condition on the proxy
    And Close the setting application
    Given Launch the agease application
    When Proxy popup is present in ageas application or not
    Then Verify proxy network identified header
    Then Verify proxy network identified message
    And Close the ageas application
    And Negative validation turn Off proxy
    Then Verify proxy popup is not appearing in ageas

  @Smoke
  Scenario: AG002 Verify VPN popup
    Given Launch the settings application
    And Tap On general icon
    And Tap On VPN and device management icon
    Then Verify if VPN is in not connected Turn on the VPN
    And Close the setting application
    Given Launch the agease application
    When VPN popup is present in ageas application or not
    Then Verify VPN network identified header
    Then Verify VPN network identified message
    And Close the ageas application
    And Negative validation turn Off VPN
    Then Verify VPN popup is not appearing in ageas
    
  @Smoke
  Scenario: AG003 Verify screen mirroring popup
    Given Launch the gmail application
    And Tap on meeting icon and tap on new meeting icon
    And Tap On start an instant meeting
    Then Verify you are shareing the screen
    And Close the gmail application
    Given Launch the agease application
    When Screen mirroring popup is present in ageas application or not
    Then Verify screen mirroring header
    Then Verify screen mirroring message
    And Close the ageas application
    And Negative validation turn Off screen mirroring
    Then Verify screen mirroring popup is not appearing in ageas