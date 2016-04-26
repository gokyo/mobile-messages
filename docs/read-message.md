Read Message
----
  Returns a specific user message as an HTML partial

* **URL**

  `/messages/read`

* **Method:**

  `POST`

*  **Request body**

    The URL provided in the request body can be obtained from the [/messages response](docs/messages.md) ```readTimeUrl``` value

```json
    {
      "url":  "/message/sa/1234512345/543e8c5f01000001003e4a9c/read-time"
    }
```

* **Success Response:**

  * **Code:** 200 <br />
    **Response body:**

Annual Tax Summary example
```hmtl
<h2>Your Annual Tax Summary for 2009-10 is now ready</h2>

<p class="message_time faded-text--small">This message was sent to you on 1 March 2016</p>

<p>This is a summary of how Government spends your tax and National Insurance contributions.</p>
<p>See your <a href="/annual-tax-summary">Annual Tax Summary</a>.</p>
<p>Tax summaries are for information only so you don't need to contact HMRC. But you can <a href="https://www.gov.uk/annual-tax-summary">comment on tax summaries</a>.</p>
```

New tax statement example:
```hmtl
<h2>You have a new tax statement</h2>
<p class="message_time faded-text--small">This message was sent to you on 13 April 2016</p>

<p>Your new Self Assessment statement has been prepared. You'll be able to view it online within 4 working days.</p>
<p>Your statement will tell you if you owe any payments to HM Revenue and Customs, or if you're due a refund.</p>

<ul>
    <li><a id="view-your-statement-href" href="https://ibt.hmrc.gov.uk/self-assessment/ind/1150863353/statements" target="_self" data-sso="client">Check if your statement is ready to view</a></li>
</ul>

```

Stopping Self Assessment example:
```hmtl
<h2>Stopping Self Assessment</h2>
<p class="message_time faded-text--small">This message was sent to you on 18 December 2015</p>

<p>You don’t have to send a tax return after the 2013 to 2014 tax year unless:</p>
<ul class="bullets">
    <li>your financial circumstances change</li>
    <li>HM Revenue and Customs tell you to</li>
</ul>

    <h3>What you still need to do</h3>
        <p>You currently owe £5,335.69</p>
    <h3>You must still send the following tax return:</h3>
    <ul class="bullets">
            <li>2013 to 2014</li>
    </ul>

<p>Send any returns and pay any tax you owe on time - there are penalties if you’re late.</p>
<ul>
    <li><a id="view-account-service-href" href="https://ibt.hmrc.gov.uk/self-assessment/ind/1150863353/account" target="_self" data-sso="client">View your account</a></li>
    <li><a id="when-to-send-tax-return-href" href="https://www.gov.uk/self-assessment-tax-returns/deadlines" target="_blank" data-sso="false" rel="external">When to send a tax return<span class="visuallyhidden">link opens in a new window</span></a></li>
    <li><a id="where-to-send-tax-return-href" href="https://ibt.hmrc.gov.uk/self-assessment/ind/1150863353/taxreturn" target="_self" data-sso="client">Send a tax return online </a></li>
    <li><a id="how-to-send-tax-return-href" href="https://www.gov.uk/self-assessment-tax-returns/sending-return" target="_blank" data-sso="false" rel="external">How to send a tax return<span class="visuallyhidden">link opens in a new window</span></a></li>
    <li><a id="sa-payment-href" href="/pay-online/self-assessment/make-a-payment" target="_self" data-sso="false">Pay a tax bill</a></li>
    <li><a id="deadline-and-penalties-href" href="https://www.gov.uk/self-assessment-tax-returns/deadlines" target="_blank" data-sso="false" rel="external">Deadlines and penalties<span class="visuallyhidden">link opens in a new window</span></a></li>
</ul>
```

Overdue payment example:
```hmtl
<h2>Pay your 2012 to 2013 tax bill now - it's overdue</h2>
<p class="message_time faded-text--small">This message was sent to you on 17 February 2016</p>

<p>You didn't pay your 2012 to 2013 tax bill on time.</p>

<p>The deadline date was 31 January 2014.</p>

<h3>What you need to do</h3>
<p>You need to pay your tax bills now. You're being charged daily interest on the amount. Legal action will be taken if you don't pay now.</p>

<h3>After you pay</h3>
<p>You will need to pay a penalty on any tax paid late. The total penalty will be confirmed after you pay this tax bill.</p>

<h3>Get help</h3>
<p>Contact HMRC if you can't pay the full amount or disagree with your tax bill. You may be able to pay in instalments, get more time to pay or appeal the tax bill.</p>
<strong>Ignore this message if you have already paid this tax bill in total.</strong>

<ul>
    <li><a id="sa-payment-href" href="/pay-online/self-assessment/make-a-payment" target="_self" data-sso="false">Make a payment</a></li>
    <li><a id="difficulties-paying-href" href="https://www.gov.uk/difficulties-paying-hmrc" target="_blank" data-sso="false" rel="external">Problems paying your tax bill<span class="visuallyhidden">link opens in a new window</span></a></li>
    <li><a id="contact-hmrc-href" href="https://www.gov.uk/government/organisations/hm-revenue-customs/contact/self-assessment" target="_blank" data-sso="false" rel="external">Contact HMRC<span class="visuallyhidden">link opens in a new window</span></a></li>
</ul>
```


* **Error Response:**

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{"code":"UNAUTHORIZED","message":"NINO does not exist on account"}`

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{"code":"LOW_CONFIDENCE_LEVEL","message":"Confidence Level on account does not allow access"}`

  * **Code:** 406 NOT ACCEPTABLE <br />
    **Content:** `{"code":"ACCEPT_HEADER_INVALID","message":"The accept header is missing or invalid"}`

  * **Code:** 500 INTERNAL_SERVER_ERROR <br />

