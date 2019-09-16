import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEtfProductMySuffix } from 'app/shared/model/etf-product-my-suffix.model';
import { getEntities as getEtfProducts } from 'app/entities/etf-product-my-suffix/etf-product-my-suffix.reducer';
import { IDealerParticipantMySuffix } from 'app/shared/model/dealer-participant-my-suffix.model';
import { getEntities as getDealerParticipants } from 'app/entities/dealer-participant-my-suffix/dealer-participant-my-suffix.reducer';
import { IEtfExecutionDtlMySuffix } from 'app/shared/model/etf-execution-dtl-my-suffix.model';
import { getEntities as getEtfExecutionDtls } from 'app/entities/etf-execution-dtl-my-suffix/etf-execution-dtl-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './redemption-my-suffix.reducer';
import { IRedemptionMySuffix } from 'app/shared/model/redemption-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IRedemptionMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IRedemptionMySuffixUpdateState {
  isNew: boolean;
  etfProductId: string;
  dealerParticipantId: string;
  etfExecutionDtlId: string;
}

export class RedemptionMySuffixUpdate extends React.Component<IRedemptionMySuffixUpdateProps, IRedemptionMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      etfProductId: '0',
      dealerParticipantId: '0',
      etfExecutionDtlId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getEtfProducts();
    this.props.getDealerParticipants();
    this.props.getEtfExecutionDtls();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { redemptionEntity } = this.props;
      const entity = {
        ...redemptionEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/redemption-my-suffix');
  };

  render() {
    const { redemptionEntity, etfProducts, dealerParticipants, etfExecutionDtls, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.redemption.home.createOrEditLabel">Create or edit a Redemption</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : redemptionEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="redemption-my-suffix-id">ID</Label>
                    <AvInput id="redemption-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="redemptionCodeLabel" for="redemption-my-suffix-redemptionCode">
                    Redemption Code
                  </Label>
                  <AvField
                    id="redemption-my-suffix-redemptionCode"
                    type="text"
                    name="redemptionCode"
                    validate={{
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="redemptionDateLabel" for="redemption-my-suffix-redemptionDate">
                    Redemption Date
                  </Label>
                  <AvField id="redemption-my-suffix-redemptionDate" type="date" className="form-control" name="redemptionDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="customerIdLabel" for="redemption-my-suffix-customerId">
                    Customer Id
                  </Label>
                  <AvField
                    id="redemption-my-suffix-customerId"
                    type="text"
                    name="customerId"
                    validate={{
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="customerNameLabel" for="redemption-my-suffix-customerName">
                    Customer Name
                  </Label>
                  <AvField
                    id="redemption-my-suffix-customerName"
                    type="text"
                    name="customerName"
                    validate={{
                      maxLength: { value: 200, errorMessage: 'This field cannot be longer than 200 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="unitSellPriceIndLabel" for="redemption-my-suffix-unitSellPriceInd">
                    Unit Sell Price Ind
                  </Label>
                  <AvField id="redemption-my-suffix-unitSellPriceInd" type="string" className="form-control" name="unitSellPriceInd" />
                </AvGroup>
                <AvGroup>
                  <Label id="unitSellPriceLabel" for="redemption-my-suffix-unitSellPrice">
                    Unit Sell Price
                  </Label>
                  <AvField id="redemption-my-suffix-unitSellPrice" type="string" className="form-control" name="unitSellPrice" />
                </AvGroup>
                <AvGroup>
                  <Label id="unitSellUnitLabel" for="redemption-my-suffix-unitSellUnit">
                    Unit Sell Unit
                  </Label>
                  <AvField id="redemption-my-suffix-unitSellUnit" type="string" className="form-control" name="unitSellUnit" />
                </AvGroup>
                <AvGroup>
                  <Label id="unitSellLotLabel" for="redemption-my-suffix-unitSellLot">
                    Unit Sell Lot
                  </Label>
                  <AvField id="redemption-my-suffix-unitSellLot" type="string" className="form-control" name="unitSellLot" />
                </AvGroup>
                <AvGroup>
                  <Label id="unitSellBasketLabel" for="redemption-my-suffix-unitSellBasket">
                    Unit Sell Basket
                  </Label>
                  <AvField id="redemption-my-suffix-unitSellBasket" type="string" className="form-control" name="unitSellBasket" />
                </AvGroup>
                <AvGroup>
                  <Label id="marketLabel" for="redemption-my-suffix-market">
                    Market
                  </Label>
                  <AvField id="redemption-my-suffix-market" type="text" name="market" />
                </AvGroup>
                <AvGroup>
                  <Label id="sellGrossAmountLabel" for="redemption-my-suffix-sellGrossAmount">
                    Sell Gross Amount
                  </Label>
                  <AvField id="redemption-my-suffix-sellGrossAmount" type="string" className="form-control" name="sellGrossAmount" />
                </AvGroup>
                <AvGroup>
                  <Label id="feeLabel" for="redemption-my-suffix-fee">
                    Fee
                  </Label>
                  <AvField id="redemption-my-suffix-fee" type="string" className="form-control" name="fee" />
                </AvGroup>
                <AvGroup>
                  <Label id="sellNetAmountLabel" for="redemption-my-suffix-sellNetAmount">
                    Sell Net Amount
                  </Label>
                  <AvField id="redemption-my-suffix-sellNetAmount" type="string" className="form-control" name="sellNetAmount" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusCashLabel" for="redemption-my-suffix-statusCash">
                    Status Cash
                  </Label>
                  <AvField id="redemption-my-suffix-statusCash" type="text" name="statusCash" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusEtfLabel" for="redemption-my-suffix-statusEtf">
                    Status Etf
                  </Label>
                  <AvField id="redemption-my-suffix-statusEtf" type="text" name="statusEtf" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="redemption-my-suffix-status">
                    Status
                  </Label>
                  <AvField id="redemption-my-suffix-status" type="text" name="status" />
                </AvGroup>
                <AvGroup>
                  <Label id="channelLabel" for="redemption-my-suffix-channel">
                    Channel
                  </Label>
                  <AvField id="redemption-my-suffix-channel" type="string" className="form-control" name="channel" />
                </AvGroup>
                <AvGroup>
                  <Label for="redemption-my-suffix-etfProduct">Etf Product</Label>
                  <AvInput id="redemption-my-suffix-etfProduct" type="select" className="form-control" name="etfProductId">
                    <option value="" key="0" />
                    {etfProducts
                      ? etfProducts.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="redemption-my-suffix-dealerParticipant">Dealer Participant</Label>
                  <AvInput id="redemption-my-suffix-dealerParticipant" type="select" className="form-control" name="dealerParticipantId">
                    <option value="" key="0" />
                    {dealerParticipants
                      ? dealerParticipants.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="redemption-my-suffix-etfExecutionDtl">Etf Execution Dtl</Label>
                  <AvInput id="redemption-my-suffix-etfExecutionDtl" type="select" className="form-control" name="etfExecutionDtlId">
                    <option value="" key="0" />
                    {etfExecutionDtls
                      ? etfExecutionDtls.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/redemption-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  etfProducts: storeState.etfProduct.entities,
  dealerParticipants: storeState.dealerParticipant.entities,
  etfExecutionDtls: storeState.etfExecutionDtl.entities,
  redemptionEntity: storeState.redemption.entity,
  loading: storeState.redemption.loading,
  updating: storeState.redemption.updating,
  updateSuccess: storeState.redemption.updateSuccess
});

const mapDispatchToProps = {
  getEtfProducts,
  getDealerParticipants,
  getEtfExecutionDtls,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RedemptionMySuffixUpdate);
