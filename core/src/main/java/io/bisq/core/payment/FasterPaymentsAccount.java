/*
 * This file is part of bisq.
 *
 * bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package io.bisq.core.payment;

import io.bisq.common.app.Version;
import io.bisq.common.locale.FiatCurrency;
import io.bisq.core.payment.payload.FasterPaymentsAccountPayload;
import io.bisq.core.payment.payload.PaymentAccountPayload;
import io.bisq.core.payment.payload.PaymentMethod;
import io.bisq.core.user.PreferencesImpl;

public final class FasterPaymentsAccount extends PaymentAccount {
    // That object is saved to disc. We need to take care of changes to not break deserialization.
    private static final long serialVersionUID = Version.LOCAL_DB_VERSION;

    public FasterPaymentsAccount() {
        super(PaymentMethod.FASTER_PAYMENTS);
        setSingleTradeCurrency(new FiatCurrency("GBP", PreferencesImpl.getDefaultLocale()));
    }

    @Override
    protected PaymentAccountPayload getPayload() {
        return new FasterPaymentsAccountPayload(paymentMethod.getId(), id, paymentMethod.getMaxTradePeriod());
    }

    public void setSortCode(String value) {
        ((FasterPaymentsAccountPayload) paymentAccountPayload).setSortCode(value);
    }

    public String getSortCode() {
        return ((FasterPaymentsAccountPayload) paymentAccountPayload).getSortCode();
    }

    public void setAccountNr(String value) {
        ((FasterPaymentsAccountPayload) paymentAccountPayload).setAccountNr(value);
    }

    public String getAccountNr() {
        return ((FasterPaymentsAccountPayload) paymentAccountPayload).getAccountNr();
    }
}
